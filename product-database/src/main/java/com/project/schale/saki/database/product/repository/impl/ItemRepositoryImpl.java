package com.project.schale.saki.database.product.repository.impl;

import com.project.schale.saki.database.product.converter.EntityConverter;
import com.project.schale.saki.database.product.entity.Item;
import com.project.schale.saki.database.product.mapper.ItemMapper;
import com.project.schale.saki.database.product.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 商品情報の取得、エンティティクラスへ変換するリポジトリ層
 */
@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemMapper mapper;

    /**
     * ロット番号に対応する商品情報を取得する
     * @param itemId ロット番号
     * @return ロット番号に対応する商品情報
     */
    public Optional<Item> findByItemId(Long itemId) {
        return EntityConverter.convert(mapper.selectByPrimaryKey(itemId), Item.class);
    }

}
