package com.api.bci.users.domain.service;

import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.port.FindUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindUserServiceTest {

    @Mock
    private FindUserRepository userRepository;

    @InjectMocks
    private FindUserService findUserService;

    @Test
    void testExecute_UserFound() {
        // Arrange
        String email = "test@test.com";
        UserResponse expectedResponse = new UserResponse();
        when(userRepository.execute(email)).thenReturn(expectedResponse);

        // Act
        UserResponse actualResponse = findUserService.execute(email);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(userRepository, times(1)).execute(email);
    }

    @Test
    void testExecute_UserNotFound() {
        // Arrange
        String email = "test@test.com";

        when(userRepository.execute(email)).thenReturn(null);

        // Act
        UserResponse actualResponse = findUserService.execute(email);

        // Assert
        assertNull(actualResponse);
        verify(userRepository, times(1)).execute(email);
    }
}
