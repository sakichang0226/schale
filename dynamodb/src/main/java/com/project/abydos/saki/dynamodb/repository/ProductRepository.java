package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.Product;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ReadBatch;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final DynamoDbEnhancedClient enhancedClient;
    private static final String TABLE_NAME = "products";

    private DynamoDbTable<Product> table() {
        return enhancedClient.table(TABLE_NAME, TableSchema.fromBean(Product.class));
    }

    public Product findById(@NonNull Long productId) {
        return table().getItem(Key.builder().partitionValue(productId).build());
    }

    public List<Product> findByIds(@NonNull List<Long> productIds) {
        ReadBatch.Builder<Product> batchBuilder = ReadBatch.builder(Product.class)
                .mappedTableResource(table());
        productIds.forEach(id -> batchBuilder.addGetItem(Key.builder().partitionValue(id).build()));

        return enhancedClient.batchGetItem(b -> b.addReadBatch(batchBuilder.build()))
                .resultsForTable(table())
                .stream()
                .toList();
    }
}
