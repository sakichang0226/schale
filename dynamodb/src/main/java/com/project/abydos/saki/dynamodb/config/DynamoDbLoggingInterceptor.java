package com.project.abydos.saki.dynamodb.config;

import com.project.abydos.saki.dynamodb.constant.DynamoDbLogMessage;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.interceptor.Context;
import software.amazon.awssdk.core.interceptor.ExecutionAttributes;
import software.amazon.awssdk.core.interceptor.ExecutionInterceptor;

/**
 * DynamoDBのリクエスト／レスポンスログ出力インターセプター
 */
@Slf4j
public class DynamoDbLoggingInterceptor implements ExecutionInterceptor {

    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeExecution(
            Context.BeforeExecution context,
            ExecutionAttributes executionAttributes) {

        log.debug(DynamoDbLogMessage.REQUEST.getMessage(), context.request());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterExecution(
            Context.AfterExecution context,
            ExecutionAttributes executionAttributes) {

        log.debug(DynamoDbLogMessage.RESPONSE.getMessage(), context.response());
    }
}
