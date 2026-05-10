package com.project.abydos.saki.dynamodb.entity;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

/**
 * 注文テーブル（orders）エンティティ.
 * user_idをPK、order_idをSKとして注文履歴をユーザー単位で管理。
 */
@Data
@DynamoDbBean
public class Order {
    /** ユーザーID（JWTから取得） (PK) */
    private Long userId;
    /** 注文ID (SK) */
    private Long orderId;
    /** 注文日時 UnixTimestamp(ms) */
    private Long createdAt;
    /** 配送ステータス PR:処理中 / ED:配送済み */
    private String deliveryStatus;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("user_id")
    public Long getUserId() { return userId; }

    @DynamoDbSortKey
    @DynamoDbAttribute("order_id")
    public Long getOrderId() { return orderId; }

    @DynamoDbAttribute("created_at")
    public Long getCreatedAt() { return createdAt; }

    @DynamoDbAttribute("delivery_status")
    public String getDeliveryStatus() { return deliveryStatus; }
}
