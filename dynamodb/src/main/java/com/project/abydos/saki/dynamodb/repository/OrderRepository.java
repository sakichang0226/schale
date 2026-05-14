package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.Order;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 注文テーブル（orders）リポジトリ.
 */
@Repository
public class OrderRepository extends AbstractDynamoDbRepository<Order> {

    public OrderRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, Order.class, "orders");
    }

    /**
     * ユーザーIDを指定して注文履歴を降順で取得する.
     *
     * @param userId ユーザーID
     * @param limit 取得件数
     * @param lastOrderId ページネーション用の最後に取得したOrderId
     * @return ページング付きクエリ結果
     */
    public PagedResult<Order> findByUserId(@NonNull Long userId, @NonNull Integer limit, Long lastOrderId) {
        QueryEnhancedRequest.Builder requestBuilder = QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(userId).build()))
                .scanIndexForward(false)
                .limit(limit);

        if (lastOrderId != null) {
            Map<String, AttributeValue> startKey = new HashMap<>();
            startKey.put("user_id", AttributeValue.builder().n(userId.toString()).build());
            startKey.put("order_id", AttributeValue.builder().n(lastOrderId.toString()).build());
            requestBuilder.exclusiveStartKey(startKey);
        }

        Page<Order> page = table().query(requestBuilder.build())
                .stream()
                .findFirst()
                .orElse(null);

        if (page == null) {
            return new PagedResult<>(Collections.emptyList(), null);
        }

        Long lastEvaluatedSortKey = null;
        Map<String, AttributeValue> lastEvaluatedKey = page.lastEvaluatedKey();
        if (lastEvaluatedKey != null && lastEvaluatedKey.containsKey("order_id")) {
            lastEvaluatedSortKey = Long.valueOf(lastEvaluatedKey.get("order_id").n());
        }

        return new PagedResult<>(page.items(), lastEvaluatedSortKey);
    }
}
