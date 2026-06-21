package com.project.abydos.saki.dynamodb.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SequenceRepositoryTest {

    @Mock
    private DynamoDbClient dynamoDbClient;

    @InjectMocks
    private SequenceRepository sequenceRepository;

    @Test
    void getNextValueで採番値が返却される() {
        UpdateItemResponse response = UpdateItemResponse.builder()
                .attributes(Map.of("current_value", AttributeValue.builder().n("5").build()))
                .build();
        when(dynamoDbClient.updateItem(any(UpdateItemRequest.class))).thenReturn(response);

        Long result = sequenceRepository.getNextValue("order_id", 1L);

        assertThat(result).isEqualTo(5L);
    }

    @Test
    void getNextValueで複数件インクリメント時に合計値が返却される() {
        UpdateItemResponse response = UpdateItemResponse.builder()
                .attributes(Map.of("current_value", AttributeValue.builder().n("10").build()))
                .build();
        when(dynamoDbClient.updateItem(any(UpdateItemRequest.class))).thenReturn(response);

        Long result = sequenceRepository.getNextValue("detail_id", 5L);

        assertThat(result).isEqualTo(10L);
    }
}
