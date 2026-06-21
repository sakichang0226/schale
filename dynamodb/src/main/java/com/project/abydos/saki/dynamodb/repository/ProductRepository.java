package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.Product;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.ReadBatch;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository extends AbstractDynamoDbRepository<Product> {

    public ProductRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, Product.class, "products");
    }

    public Optional<Product> findById(@NonNull Long productId) {
        return Optional.ofNullable(table().getItem(Key.builder().partitionValue(productId).build()));
    }

    public List<Product> findByIds(@NonNull List<Long> productIds) {
        ReadBatch.Builder<Product> batchBuilder = ReadBatch.builder(Product.class)
                .mappedTableResource(table());
        productIds.forEach(id -> batchBuilder.addGetItem(Key.builder().partitionValue(id).build()));

        return client().batchGetItem(b -> b.addReadBatch(batchBuilder.build()))
                .resultsForTable(table())
                .stream()
                .toList();
    }
}
