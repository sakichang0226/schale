package com.project.schale.saki.database.product.repository.impl;

import com.project.schale.saki.database.product.converter.EntityConverter;
import com.project.schale.saki.database.product.entity.Shop;
import com.project.schale.saki.database.product.mapper.ShopMapper;
import com.project.schale.saki.database.product.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *　店舗情報の取得、エンティティクラスへ変換するリポジトリ層
 */
@Repository
@RequiredArgsConstructor
public class ShopRepositoryImpl implements ShopRepository {

    private final ShopMapper mapper;

    /**
     * 店舗IDを指定し、対応する店舗情報を取得する
     * @param shopId　店舗Id
     * @return 店舗情報
     */
    public Optional<Shop> findOne(Long shopId) {
        return EntityConverter.convert(mapper.selectByPrimaryKey(shopId), Shop.class);
    }

}
