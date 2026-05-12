package com.project.abydos.saki.dynamodb.entity;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

/**
 * 採番用カウンターテーブル（sequences）エンティティ.
 * UpdateItemのADD操作でアトミックにインクリメントし、一意な連番IDを発行する。
 */
@Data
@DynamoDbBean
public class Sequence {
    /** 採番対象の識別名（例: "order_id", "sub_order_id"） (PK) */
    private String sequenceName;
    /** 現在の採番値 */
    private Long currentValue;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("sequence_name")
    public String getSequenceName() { return sequenceName; }

    @DynamoDbAttribute("current_value")
    public Long getCurrentValue() { return currentValue; }
}
