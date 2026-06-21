package com.project.abydos.saki.dynamodb.entity;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

/**
 * 商品レビューテーブル（reviews）エンティティ.
 * 商品詳細画面でのレビュー表示に使用。
 *
 * <p>LSI:
 * <ul>
 *   <li>rating-index (PK: product_id, SK: rating)</li>
 * </ul>
 *
 * <p>GSI:
 * <ul>
 *   <li>user-review-index (PK: user_id, SK: created_at)</li>
 * </ul>
 */
@Data
@DynamoDbBean
public class Review {
    /** 商品ID (PK) */
    private Long productId;
    /** レビューID (SK) */
    private Long reviewId;
    /** 投稿者ID (GSI1-PK) */
    private Long userId;
    /** 評価スコア（1〜5） (LSI1-SK) */
    private Long rating;
    /** レビューコメント */
    private String comment;
    /** 投稿日時 UnixTimestamp(ms) (GSI1-SK) */
    private Long createdAt;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("product_id")
    public Long getProductId() { return productId; }

    @DynamoDbSortKey
    @DynamoDbAttribute("review_id")
    public Long getReviewId() { return reviewId; }

    @DynamoDbAttribute("user_id")
    public Long getUserId() { return userId; }

    @DynamoDbAttribute("created_at")
    public Long getCreatedAt() { return createdAt; }
}
