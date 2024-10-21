package com.project.schale.saki.database.product.repository;

import com.project.schale.saki.database.product.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository {

    public Optional<Shop> findOne(Long shopId);

}
