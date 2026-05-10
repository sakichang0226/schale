package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final DynamoDbEnhancedClient enhancedClient;
    private static final String TABLE_NAME = "users";
    private static final String EMAIL_INDEX = "email-index";

    private DynamoDbTable<User> table() {
        return enhancedClient.table(TABLE_NAME, TableSchema.fromBean(User.class));
    }

    public Optional<User> findByEmail(@NonNull String email) {
        DynamoDbIndex<User> index = table().index(EMAIL_INDEX);
        return index.query(QueryConditional.keyEqualTo(Key.builder().partitionValue(email).build()))
                .stream()
                .flatMap(page -> page.items().stream())
                .findFirst();
    }
}
