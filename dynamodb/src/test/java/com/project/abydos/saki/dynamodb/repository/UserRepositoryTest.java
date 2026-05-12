package com.project.abydos.saki.dynamodb.repository;

import com.project.abydos.saki.dynamodb.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private DynamoDbEnhancedClient enhancedClient;

    @Mock
    private DynamoDbTable<User> table;

    @Mock
    private DynamoDbIndex<User> index;

    @Mock
    private SdkIterable<Page<User>> queryResults;

    @InjectMocks
    private UserRepository userRepository;

    @Test
    void findByEmailでユーザーが見つかる場合() {
        User user = new User();
        user.setUserId(1L);
        user.setEmail("test@example.com");
        user.setUserName("テストユーザー");

        doReturn(table).when(enhancedClient).table(eq("users"), any(TableSchema.class));
        when(table.index("email-index")).thenReturn(index);
        doReturn(queryResults).when(index).query(any(QueryConditional.class));

        Page<User> page = Page.create(List.of(user));
        when(queryResults.stream()).thenReturn(Stream.of(page));

        Optional<User> result = userRepository.findByEmail("test@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getUserId()).isEqualTo(1L);
        assertThat(result.get().getEmail()).isEqualTo("test@example.com");
        assertThat(result.get().getUserName()).isEqualTo("テストユーザー");
    }

    @Test
    void findByEmailでユーザーが見つからない場合() {
        doReturn(table).when(enhancedClient).table(eq("users"), any(TableSchema.class));
        when(table.index("email-index")).thenReturn(index);
        doReturn(queryResults).when(index).query(any(QueryConditional.class));

        Page<User> emptyPage = Page.create(List.of());
        when(queryResults.stream()).thenReturn(Stream.of(emptyPage));

        Optional<User> result = userRepository.findByEmail("notfound@example.com");

        assertThat(result).isEmpty();
    }
}
