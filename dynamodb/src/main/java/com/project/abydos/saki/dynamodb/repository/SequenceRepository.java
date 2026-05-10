package com.project.abydos.saki.dynamodb.repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ReturnValue;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class SequenceRepository {

    private final DynamoDbClient dynamoDbClient;
    private static final String TABLE_NAME = "sequences";

    public Long getNextValue(@NonNull String sequenceName, @NonNull Long increment) {
        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(Map.of("sequence_name", AttributeValue.builder().s(sequenceName).build()))
                .updateExpression("ADD current_value :incr")
                .expressionAttributeValues(Map.of(":incr", AttributeValue.builder().n(increment.toString()).build()))
                .returnValues(ReturnValue.UPDATED_NEW)
                .build();

        UpdateItemResponse response = dynamoDbClient.updateItem(request);
        return Long.parseLong(response.attributes().get("current_value").n());
    }
}
