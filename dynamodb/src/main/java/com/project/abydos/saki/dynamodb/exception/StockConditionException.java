package com.project.abydos.saki.dynamodb.exception;

/**
 * トランザクション実行時に在庫条件が満たされなかった場合の例外.
 */
public class StockConditionException extends RuntimeException {

    public StockConditionException(String message, Throwable cause) {
        super(message, cause);
    }
}
