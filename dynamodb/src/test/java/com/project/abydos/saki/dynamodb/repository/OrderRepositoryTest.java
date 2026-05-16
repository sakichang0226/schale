package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.exception.StockConditionException;
import com.project.abydos.saki.dynamodb.param.OrderTransactionParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.TransactWriteItemsRequest;
import software.amazon.awssdk.services.dynamodb.model.TransactionCanceledException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderRepositoryTest {

    @Mock
    private DynamoDbEnhancedClient enhancedClient;

    @Mock
    private DynamoDbClient dynamoDbClient;

    @Mock
    private DynamoDbTable<?> table;

    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        when(enhancedClient.table(eq("orders"), any())).thenReturn((DynamoDbTable) table);
        orderRepository = new OrderRepository(enhancedClient, dynamoDbClient);
    }

    @Test
    void saveOrder_TransactionCanceledException発生時にStockConditionExceptionがスローされる() {
        when(dynamoDbClient.transactWriteItems(any(TransactWriteItemsRequest.class)))
                .thenThrow(TransactionCanceledException.builder().message("ConditionalCheckFailed").build());

        OrderTransactionParam param = OrderTransactionParam.builder()
                .userId(1L)
                .orderId(100L)
                .firstDetailId(200L)
                .createdAt(System.currentTimeMillis())
                .initialDeliveryStatus("PR")
                .details(List.of(
                        OrderTransactionParam.DetailParam.builder()
                                .productId(1L)
                                .productName("商品A")
                                .shopId(10001L)
                                .price(1000L)
                                .quantity(2L)
                                .build()
                ))
                .build();

        assertThatThrownBy(() -> orderRepository.saveOrder(param))
                .isInstanceOf(StockConditionException.class);
    }

    @Test
    void saveOrder_トランザクションアイテム数が100件を超える場合にIllegalArgumentExceptionがスローされる() {
        List<OrderTransactionParam.DetailParam> details = java.util.stream.IntStream.range(0, 100)
                .mapToObj(i -> OrderTransactionParam.DetailParam.builder()
                        .productId((long) i)
                        .productName("商品" + i)
                        .shopId(10001L)
                        .price(1000L)
                        .quantity(1L)
                        .build())
                .toList();

        OrderTransactionParam param = OrderTransactionParam.builder()
                .userId(1L)
                .orderId(100L)
                .firstDetailId(200L)
                .createdAt(System.currentTimeMillis())
                .initialDeliveryStatus("PR")
                .details(details)
                .build();

        // 1(order) + 100(details) + 100(stock updates) = 201 > 100
        assertThatThrownBy(() -> orderRepository.saveOrder(param))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
