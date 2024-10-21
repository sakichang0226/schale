package com.project.schale.saki.database.product.repository;

import com.project.schale.saki.database.product.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    public List<Category> findAll();

}
