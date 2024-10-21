package com.project.schale.saki.database.product.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 店舗情報のエンティティクラス
 */
@Getter
@Setter
public class Shop {

    /** 店舗Id　*/
    private Long shopId;

    /** 店舗名　*/
    private String name;

    /** 作成日　*/
    private LocalDateTime createAt;

    /** 更新日　*/
    private LocalDateTime updateAt;

}
