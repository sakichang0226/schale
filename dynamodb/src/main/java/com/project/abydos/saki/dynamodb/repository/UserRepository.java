package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.User;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.Optional;

@Repository
public class UserRepository extends AbstractDynamoDbRepository<User> {

    private static final String EMAIL_INDEX = "email-index";

    public UserRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, User.class, "users");
    }

    public Optional<User> findByEmail(@NonNull String email) {
        DynamoDbIndex<User> index = table().index(EMAIL_INDEX);
        return index.query(QueryConditional.keyEqualTo(Key.builder().partitionValue(email).build()))
                .stream()
                .flatMap(page -> page.items().stream())
                .findFirst();
    }
}
