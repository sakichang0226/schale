package com.project.abydos.saki.dynamodb.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * DynamoDBリポジトリ層のエラーメッセージ定数.
 */
@Getter
@RequiredArgsConstructor
public enum DynamoDbErrorMessage {

    /** トランザクション競合による在庫条件不一致 */
    STOCK_CONDITION_NOT_MET("Transaction conflict: {}", "stock condition not met");

    private final String message;
    private final String detailFormat;

    /**
     * 詳細メッセージをフォーマットする.
     *
     * @param args フォーマット引数
     * @return フォーマット済み詳細メッセージ
     */
    public String formatDetail(Object... args) {
        return String.format(detailFormat, args);
    }
}
