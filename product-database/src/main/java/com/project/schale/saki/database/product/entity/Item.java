package com.project.schale.saki.database.product.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *　商品情報のエンティティクラス
 */
@Getter
@Setter
public class Item {

    /** 商品Id　*/
    private Long itemId;

    /** 商品名　*/
    private String name;

    /** 店舗Id　*/
    private Long shopId;

    /** カテゴリId　*/
    private Long categoryId;

    /** 商品価格　*/
    private Long price;

    /** 購入点数　*/
    private Integer purchaseNum;

    /** 在庫　*/
    private Integer stock;

    /** 販売停止フラグ　*/
    private Boolean is_stopped;

    /** 商品説明 */
    private String description;

    /** 作成日　*/
    private LocalDateTime createAt;

    /** 更新日　*/
    private LocalDateTime updateAt;

}
