package com.project.abydos.saki.dynamodb.repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

public abstract class AbstractDynamoDbRepository<T> {

    private final DynamoDbEnhancedClient enhancedClient;
    private final Class<T> entityClass;
    private final String tableName;

    protected AbstractDynamoDbRepository(DynamoDbEnhancedClient enhancedClient, Class<T> entityClass, String tableName) {
        this.enhancedClient = enhancedClient;
        this.entityClass = entityClass;
        this.tableName = tableName;
    }

    protected DynamoDbTable<T> table() {
        return enhancedClient.table(tableName, TableSchema.fromBean(entityClass));
    }

    protected DynamoDbEnhancedClient client() {
        return enhancedClient;
    }
}
