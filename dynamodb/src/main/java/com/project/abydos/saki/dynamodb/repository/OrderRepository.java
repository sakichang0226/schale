package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.Order;
import com.project.abydos.saki.dynamodb.mapper.OrderTransactionMapper;
import com.project.abydos.saki.dynamodb.param.OrderTransactionParam;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.TransactWriteItem;
import software.amazon.awssdk.services.dynamodb.model.TransactWriteItemsRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注文テーブル（orders）リポジトリ.
 */
@Repository
public class OrderRepository extends AbstractDynamoDbRepository<Order> {

    private final DynamoDbClient dynamoDbClient;
    private final OrderTransactionMapper transactionMapper;

    private static final int TRANSACT_WRITE_ITEM_LIMIT = 100;

    public OrderRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDbClient) {
        super(enhancedClient, Order.class, "orders");
        this.dynamoDbClient = dynamoDbClient;
        this.transactionMapper = new OrderTransactionMapper();
    }

    /**
     * ユーザーIDを指定して注文履歴を降順で取得する.
     *
     * @param userId ユーザーID
     * @param limit 取得件数
     * @param lastOrderId ページネーション用の最後に取得したOrderId
     * @return ページング付きクエリ結果
     */
    public PagedResult<Order> findByUserId(@NonNull Long userId, @NonNull Integer limit, Long lastOrderId) {
        QueryEnhancedRequest.Builder requestBuilder = QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(userId).build()))
                .scanIndexForward(false)
                .limit(limit);

        if (lastOrderId != null) {
            Map<String, AttributeValue> startKey = new HashMap<>();
            startKey.put("user_id", AttributeValue.builder().n(userId.toString()).build());
            startKey.put("order_id", AttributeValue.builder().n(lastOrderId.toString()).build());
            requestBuilder.exclusiveStartKey(startKey);
        }

        Page<Order> page = table().query(requestBuilder.build())
                .stream()
                .findFirst()
                .orElse(null);

        if (page == null) {
            return new PagedResult<>(Collections.emptyList(), null);
        }

        Long lastEvaluatedSortKey = null;
        Map<String, AttributeValue> lastEvaluatedKey = page.lastEvaluatedKey();
        if (lastEvaluatedKey != null && lastEvaluatedKey.containsKey("order_id")) {
            lastEvaluatedSortKey = Long.valueOf(lastEvaluatedKey.get("order_id").n());
        }

        return new PagedResult<>(page.items(), lastEvaluatedSortKey);
    }

    /**
     * 注文登録トランザクションを実行する.
     * orders/order_detailsのPutとproductsの在庫減算Updateを1トランザクションで実行する.
     *
     * @param param トランザクションパラメータ
     * @throws IllegalArgumentException トランザクションアイテム数が上限を超える場合
     */
    public void saveOrder(@NonNull OrderTransactionParam param) {
        List<TransactWriteItem> transactItems = transactionMapper.toTransactWriteItems(param);

        if (transactItems.size() > TRANSACT_WRITE_ITEM_LIMIT) {
            throw new IllegalArgumentException("Transaction item count exceeds limit: " + transactItems.size());
        }

        dynamoDbClient.transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(transactItems)
                .build());
    }
}
