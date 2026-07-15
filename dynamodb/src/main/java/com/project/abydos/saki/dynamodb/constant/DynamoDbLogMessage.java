package com.project.abydos.saki.dynamodb.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * DynamoDBパッケージのログメッセージ定数.
 */
@Getter
@RequiredArgsConstructor
public enum DynamoDbLogMessage {

    /** Dynamo DBテーブルへのリクエスト時のログ */
    REQUEST("Request={}"),

    /** Dynamo DBテーブルのレスポンスログ */
    RESPONSE("Request={}");

    private final String message;

}
