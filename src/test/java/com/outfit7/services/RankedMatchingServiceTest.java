package com.outfit7.services;

import static com.outfit7.utils.TestUtils.user;
import static com.outfit7.utils.TestUtils.users;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.outfit7.entity.User;

@ExtendWith(MockitoExtension.class)
public class RankedMatchingServiceTest {

    @Mock
    UserService userService;

    @InjectMocks
    ClassicMatchingService classicMatchingService;

    @Test
    void shouldRetrieveOpponentsForUserId() {
        // Given
        String userId = "some-user-id";
        given(userService.get(eq(userId)))
                .willReturn(user());
        given(userService.getAll())
                .willReturn(users());

        // When
        List<User> opponents = classicMatchingService.retrieveOpponents(userId);

        // Then
        // Changed a few values (removed no. 6) - second assignment
        assertThat(opponents)
                .hasSize(3)
                .extracting(User::getId)
                .containsExactly("2", "3", "5");
    }
}
