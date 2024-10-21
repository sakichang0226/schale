package com.project.schale.saki.database.product.entity;

import java.time.LocalDateTime;

/**
 *　商品画像情報のエンティティクラス
 */
public class Image {

    /** 店舗Id　*/
    private Long imageId;

    /** 商品Id　*/
    private Long itemId;

    /** 商品画像名　*/
    private String name;

    /** 作成日　*/
    private LocalDateTime createAt;

    /** 更新日　*/
    private LocalDateTime updateAt;


}
