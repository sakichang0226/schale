package com.project.schale.saki.database.product.entity;

import java.time.LocalDateTime;

/**
 *　カテゴリ情報のエンティティクラス
 */
public class Category {

    /** カテゴリId　*/
    private Long categoryId;

    /** 親カテゴリId　*/
    private Long parentCategoryId;

    /** カテゴリ名　*/
    private String name;

    /** カテゴリ階層　*/
    private Integer depth;

    /** 作成日　*/
    private LocalDateTime createAt;

    /** 更新日　*/
    private LocalDateTime updateAt;
}
