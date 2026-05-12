package com.project.abydos.saki.dynamodb.entity;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

/**
 * 商品マスタテーブル（products）エンティティ.
 * 商品一覧・詳細・TOP画面表示に使用。
 *
 * <p>GSI:
 * <ul>
 *   <li>shop-product-index (PK: shop_id, SK: product_id)</li>
 *   <li>category-product-index (PK: category_id, SK: created_at)</li>
 * </ul>
 */
@Data
@DynamoDbBean
public class Product {
    /** ロット番号 (PK) */
    private Long productId;
    /** 商品名 */
    private String productName;
    /** 商品説明 */
    private String description;
    /** S3の商品画像URL */
    private String imageUrl;
    /** 店舗ID (GSI1-PK) */
    private Long shopId;
    /** カテゴリID (GSI2-PK) */
    private Long categoryId;
    /** 価格（税計算済み） */
    private Long price;
    /** 税種別 I:内税 / E:外税 / N:税無し */
    private String taxType;
    /** 評価スコア（例: 4.0） */
    private Double rating;
    /** レビュー件数 */
    private Long reviewCount;
    /** 在庫数 */
    private Long stock;
    /** 販売ステータス O:販売中 / S:売切 / D:販売終了 */
    private String status;
    /** 作成日時 UnixTimestamp(ms) (GSI2-SK) */
    private Long createdAt;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("product_id")
    public Long getProductId() { return productId; }

    @DynamoDbAttribute("product_name")
    public String getProductName() { return productName; }

    @DynamoDbAttribute("image_url")
    public String getImageUrl() { return imageUrl; }

    @DynamoDbAttribute("shop_id")
    public Long getShopId() { return shopId; }

    @DynamoDbAttribute("category_id")
    public Long getCategoryId() { return categoryId; }

    @DynamoDbAttribute("tax_type")
    public String getTaxType() { return taxType; }

    @DynamoDbAttribute("review_count")
    public Long getReviewCount() { return reviewCount; }

    @DynamoDbAttribute("created_at")
    public Long getCreatedAt() { return createdAt; }
}
