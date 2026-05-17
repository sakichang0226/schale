package com.project.abydos.saki.dynamodb.param;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 注文登録トランザクションのパラメータ.
 */
@Getter
@Builder
public class OrderTransactionParam {

    private final Long userId;
    private final Long orderId;
    private final Long firstDetailId;
    private final long createdAt;
    private final String initialDeliveryStatus;
    private final List<DetailParam> details;

    /**
     * 注文明細パラメータ.
     */
    @Getter
    @Builder
    public static class DetailParam {
        private final Long productId;
        private final String productName;
        private final Long shopId;
        private final Long price;
        private final Long quantity;
    }
}
