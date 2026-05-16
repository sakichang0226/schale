package com.project.abydos.saki.dynamodb.entity;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

/**
 * 受注明細テーブル（order_details）エンティティ.
 * 注文に紐づく商品明細を商品単位で管理。1注文に対して複数の明細を保持する。
 *
 * <p>GSI:
 * <ul>
 *   <li>shop-id-index (PK: shop_id, SK: created_at)</li>
 * </ul>
 */
@Data
@DynamoDbBean
public class OrderDetail {
    /** 注文ID（ordersテーブルのSKと対応） (PK) */
    private Long orderId;
    /** 受注明細ID（商品単位の連番） (SK) */
    private Long detailId;
    /** ユーザーID */
    private Long userId;
    /** 店舗ID (GSI1-PK) */
    private Long shopId;
    /** 商品ID */
    private Long productId;
    /** 商品名（注文時点のスナップショット） */
    private String productName;
    /** 注文時単価 */
    private Long price;
    /** 注文数 */
    private Long orderNum;
    /** 受注日時 UnixTimestamp(ms) (GSI1-SK) */
    private Long createdAt;
    /** 配送ステータス（PR:処理中 / ED:配送済み） */
    private String deliveryStatus;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("order_id")
    public Long getOrderId() { return orderId; }

    @DynamoDbSortKey
    @DynamoDbAttribute("detail_id")
    public Long getDetailId() { return detailId; }

    @DynamoDbAttribute("user_id")
    public Long getUserId() { return userId; }

    @DynamoDbAttribute("shop_id")
    public Long getShopId() { return shopId; }

    @DynamoDbAttribute("product_id")
    public Long getProductId() { return productId; }

    @DynamoDbAttribute("product_name")
    public String getProductName() { return productName; }

    @DynamoDbAttribute("price")
    public Long getPrice() { return price; }

    @DynamoDbAttribute("order_num")
    public Long getOrderNum() { return orderNum; }

    @DynamoDbAttribute("created_at")
    public Long getCreatedAt() { return createdAt; }

    @DynamoDbAttribute("delivery_status")
    public String getDeliveryStatus() { return deliveryStatus; }
}
