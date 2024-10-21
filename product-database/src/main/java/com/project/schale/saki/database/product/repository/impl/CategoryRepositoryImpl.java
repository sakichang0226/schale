package com.project.schale.saki.database.product.repository.impl;

import com.project.schale.saki.database.product.converter.EntityConverter;
import com.project.schale.saki.database.product.entity.Category;
import com.project.schale.saki.database.product.mapper.CategoryMapper;
import com.project.schale.saki.database.product.model.CategoryExample;
import com.project.schale.saki.database.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * カテゴリ情報に関するリポジトリクラス
 */
@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryMapper mapper;

    /**
     * カテゴリ情報を取得する
     * @return カテゴリ情報
     */
    public List<Category> findAll() {
        List<com.project.schale.saki.database.product.model.Category> result =  mapper.selectByExample(new CategoryExample());

        return EntityConverter.convert(result, Category.class);
    }

}
