package com.project.abydos.saki.dynamodb.mapper;

import com.project.abydos.saki.dynamodb.param.OrderTransactionParam;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

/**
 * 注文登録トランザクション用Mapper.
 * OrderTransactionParamからTransactWriteItemリストを構築する.
 */
public class OrderTransactionMapper {

    private static final String ORDERS_TABLE = "orders";
    private static final String ORDER_DETAILS_TABLE = "order_details";
    private static final String PRODUCTS_TABLE = "products";

    /**
     * トランザクションパラメータからTransactWriteItemリストを構築する.
     *
     * @param param トランザクションパラメータ
     * @return TransactWriteItemリスト
     */
    public List<TransactWriteItem> toTransactWriteItems(OrderTransactionParam param) {
        List<OrderTransactionParam.DetailParam> details = param.getDetails();
        List<TransactWriteItem> items = new ArrayList<>(1 + details.size() * 2);

        items.add(buildOrderPut(param));

        long detailIdSeq = param.getFirstDetailId();
        for (OrderTransactionParam.DetailParam detail : details) {
            items.add(buildOrderDetailPut(param, detail, detailIdSeq++));
        }

        for (OrderTransactionParam.DetailParam detail : details) {
            items.add(buildStockUpdate(detail));
        }

        return items;
    }

    private TransactWriteItem buildOrderPut(OrderTransactionParam param) {
        Set<String> detailIdNs = new LinkedHashSet<>();
        long firstId = param.getFirstDetailId();
        for (int i = 0; i < param.getDetails().size(); i++) {
            detailIdNs.add(String.valueOf(firstId + i));
        }

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("user_id", AttributeValue.builder().n(param.getUserId().toString()).build());
        item.put("order_id", AttributeValue.builder().n(param.getOrderId().toString()).build());
        item.put("created_at", AttributeValue.builder().n(String.valueOf(param.getCreatedAt())).build());
        item.put("detail_ids", AttributeValue.builder().ns(detailIdNs).build());

        return TransactWriteItem.builder()
                .put(Put.builder().tableName(ORDERS_TABLE).item(item).build())
                .build();
    }

    private TransactWriteItem buildOrderDetailPut(OrderTransactionParam param,
                                                   OrderTransactionParam.DetailParam detail,
                                                   long detailId) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("order_id", AttributeValue.builder().n(param.getOrderId().toString()).build());
        item.put("detail_id", AttributeValue.builder().n(String.valueOf(detailId)).build());
        item.put("user_id", AttributeValue.builder().n(param.getUserId().toString()).build());
        item.put("product_id", AttributeValue.builder().n(detail.getProductId().toString()).build());
        item.put("product_name", AttributeValue.builder().s(detail.getProductName()).build());
        item.put("shop_id", AttributeValue.builder().n(detail.getShopId().toString()).build());
        item.put("price", AttributeValue.builder().n(detail.getPrice().toString()).build());
        item.put("order_num", AttributeValue.builder().n(detail.getQuantity().toString()).build());
        item.put("created_at", AttributeValue.builder().n(String.valueOf(param.getCreatedAt())).build());
        item.put("delivery_status", AttributeValue.builder().s(param.getInitialDeliveryStatus()).build());

        return TransactWriteItem.builder()
                .put(Put.builder().tableName(ORDER_DETAILS_TABLE).item(item).build())
                .build();
    }

    private TransactWriteItem buildStockUpdate(OrderTransactionParam.DetailParam detail) {
        return TransactWriteItem.builder()
                .update(Update.builder()
                        .tableName(PRODUCTS_TABLE)
                        .key(Map.of("product_id", AttributeValue.builder().n(detail.getProductId().toString()).build()))
                        .updateExpression("SET stock = stock - :qty")
                        .conditionExpression("stock >= :qty")
                        .expressionAttributeValues(Map.of(
                                ":qty", AttributeValue.builder().n(detail.getQuantity().toString()).build()))
                        .build())
                .build();
    }
}
