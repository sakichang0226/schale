package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.SubOrder;
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
 * 受注明細テーブル（sub_orders）リポジトリ.
 */
@Repository
public class SubOrderRepository extends AbstractDynamoDbRepository<SubOrder> {

    private static final int BATCH_GET_ITEM_LIMIT = 100;

    public SubOrderRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, SubOrder.class, "sub_orders");
    }

    /**
     * order_idとsub_order_idsのマップからBatchGetItemで一括取得する.
     * 100件を超える場合は分割して実行する。
     */
    public List<SubOrder> batchGetByOrderSubOrderIds(@NonNull Map<Long, Set<Long>> orderSubOrderIdsMap) {
        List<Key> keys = new ArrayList<>();
        orderSubOrderIdsMap.forEach((orderId, subOrderIds) ->
                subOrderIds.forEach(subOrderId ->
                        keys.add(Key.builder().partitionValue(orderId).sortValue(subOrderId).build())
                )
        );

        List<SubOrder> results = new ArrayList<>();
        for (int i = 0; i < keys.size(); i += BATCH_GET_ITEM_LIMIT) {
            List<Key> chunk = keys.subList(i, Math.min(i + BATCH_GET_ITEM_LIMIT, keys.size()));

            ReadBatch.Builder<SubOrder> batchBuilder = ReadBatch.builder(SubOrder.class)
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
