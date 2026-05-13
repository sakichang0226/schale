package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.Order;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;

@Repository
public class OrderRepository extends AbstractDynamoDbRepository<Order> {

    public OrderRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, Order.class, "orders");
    }

    public List<Order> findByUserId(@NonNull Long userId, @NonNull Integer limit, Long lastOrderId) {
        QueryEnhancedRequest.Builder requestBuilder = QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(userId).build()))
                .scanIndexForward(false)
                .limit(limit);

        if (lastOrderId != null) {
            requestBuilder.exclusiveStartKey(
                    new java.util.HashMap<>() {{
                        put("user_id", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().n(userId.toString()).build());
                        put("order_id", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().n(lastOrderId.toString()).build());
                    }}
            );
        }

        return table().query(requestBuilder.build())
                .stream()
                .flatMap(page -> page.items().stream())
                .toList();
    }
}
