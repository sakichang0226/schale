package com.project.schale.saki.database.product.repository.impl;

import com.project.schale.saki.database.product.converter.EntityConverter;
import com.project.schale.saki.database.product.entity.Image;
import com.project.schale.saki.database.product.mapper.ImageMapper;
import com.project.schale.saki.database.product.model.ImageExample;
import com.project.schale.saki.database.product.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 商品画像に関するリポジトリクラス
 */
@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {

    private final ImageMapper mapper;

    /**
     * 商品Idに対応する商品画像を1件取得する
     * @param itemId ロット番号
     * @return 商品画像
     */
    public Optional<Image> findOneByItemId(Long itemId) {

        ImageExample example = new ImageExample();
        ImageExample.Criteria criteria = example.createCriteria().andItemIdEqualTo(itemId);

        return EntityConverter.convert(mapper.selectByExample(example).stream().findFirst(), Image.class);
    }

}
