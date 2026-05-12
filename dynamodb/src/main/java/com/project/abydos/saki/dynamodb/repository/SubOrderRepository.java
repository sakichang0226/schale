package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.SubOrder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubOrderRepository {

    private final DynamoDbEnhancedClient enhancedClient;
    private static final String TABLE_NAME = "sub_orders";

    private DynamoDbTable<SubOrder> table() {
        return enhancedClient.table(TABLE_NAME, TableSchema.fromBean(SubOrder.class));
    }

    public List<SubOrder> findByOrderId(@NonNull Long orderId) {
        return table().query(QueryConditional.keyEqualTo(Key.builder().partitionValue(orderId).build()))
                .stream()
                .flatMap(page -> page.items().stream())
                .toList();
    }
}
