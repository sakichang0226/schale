package com.project.schale.saki.database.product.repository;

import com.project.schale.saki.database.product.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository {

    public Optional<Item> findByItemId(Long itemId);

}
