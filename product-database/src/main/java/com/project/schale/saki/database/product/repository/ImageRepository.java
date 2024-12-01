package com.project.schale.saki.database.product.repository;

import com.project.schale.saki.database.product.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ImageRepository {

    public Optional<Image> findOneByItemId(Long itemId);

}
