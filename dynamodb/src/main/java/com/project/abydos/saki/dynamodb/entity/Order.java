package com.project.abydos.saki.dynamodb.entity;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.Set;

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
    /** 受注明細IDのSet（商品単位の連番） */
    private Set<Long> detailIds;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("user_id")
    public Long getUserId() { return userId; }

    @DynamoDbSortKey
    @DynamoDbAttribute("order_id")
    public Long getOrderId() { return orderId; }

    @DynamoDbAttribute("created_at")
    public Long getCreatedAt() { return createdAt; }

    @DynamoDbAttribute("detail_ids")
    public Set<Long> getDetailIds() { return detailIds; }
}
