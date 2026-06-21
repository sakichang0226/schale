package com.project.abydos.saki.dynamodb.entity;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

/**
 * ユーザー認証・ログイン用テーブル（users）エンティティ.
 *
 * <p>GSI:
 * <ul>
 *   <li>email-index (PK: email)</li>
 * </ul>
 */
@Data
@DynamoDbBean
public class User {
    /** ユーザーID (PK) */
    private Long userId;
    /** メールアドレス（ログイン検索用） (GSI1-PK) */
    private String email;
    /** bcryptハッシュ化パスワード */
    private String passwordHash;
    /** 表示名 */
    private String userName;
    /** 作成日時 UnixTimestamp(ms) */
    private Long createdAt;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("user_id")
    public Long getUserId() { return userId; }

    @DynamoDbSecondaryPartitionKey(indexNames = "email-index")
    @DynamoDbAttribute("email")
    public String getEmail() { return email; }

    @DynamoDbAttribute("password_hash")
    public String getPasswordHash() { return passwordHash; }

    @DynamoDbAttribute("user_name")
    public String getUserName() { return userName; }

    @DynamoDbAttribute("created_at")
    public Long getCreatedAt() { return createdAt; }
}
