package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.OrderDetail;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.BatchGetItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.BatchGetResultPageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.ReadBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 受注明細テーブル（order_details）リポジトリ.
 */
@Repository
public class OrderDetailRepository extends AbstractDynamoDbRepository<OrderDetail> {

    private static final int BATCH_GET_ITEM_LIMIT = 100;

    public OrderDetailRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, OrderDetail.class, "order_details");
    }

    /**
     * order_idとdetail_idsのマップからBatchGetItemで一括取得する.
     * 100件を超える場合は分割して実行する。
     */
    public List<OrderDetail> batchGetByOrderDetailIds(@NonNull Map<Long, Set<Long>> orderDetailIdsMap) {
        List<Key> keys = new ArrayList<>();
        orderDetailIdsMap.forEach((orderId, detailIds) ->
                detailIds.forEach(detailId ->
                        keys.add(Key.builder().partitionValue(orderId).sortValue(detailId).build())
                )
        );

        List<OrderDetail> results = new ArrayList<>();
        for (int i = 0; i < keys.size(); i += BATCH_GET_ITEM_LIMIT) {
            List<Key> chunk = keys.subList(i, Math.min(i + BATCH_GET_ITEM_LIMIT, keys.size()));

            ReadBatch.Builder<OrderDetail> batchBuilder = ReadBatch.builder(OrderDetail.class)
                    .mappedTableResource(table());
            chunk.forEach(batchBuilder::addGetItem);

            BatchGetResultPageIterable resultPage = client().batchGetItem(
                    BatchGetItemEnhancedRequest.builder()
                            .readBatches(batchBuilder.build())
                            .build()
            );
            results.addAll(resultPage.resultsForTable(table()).stream().toList());
        }
        return results;
    }
}
