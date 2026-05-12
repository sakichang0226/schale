package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.SubOrder;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;

@Repository
public class SubOrderRepository extends AbstractDynamoDbRepository<SubOrder> {

    public SubOrderRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, SubOrder.class, "sub_orders");
    }

    public List<SubOrder> findByOrderId(@NonNull Long orderId) {
        return table().query(QueryConditional.keyEqualTo(Key.builder().partitionValue(orderId).build()))
                .stream()
                .flatMap(page -> page.items().stream())
                .toList();
    }
}
