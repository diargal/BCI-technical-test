package com.api.bci.users.domain.service;

import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.port.FindUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

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
        UUID id = UUID.randomUUID();
        UserResponse expectedResponse = new UserResponse();
        when(userRepository.execute(id)).thenReturn(expectedResponse);

        // Act
        UserResponse actualResponse = findUserService.execute(id);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(userRepository, times(1)).execute(id);
    }

    @Test
    void testExecute_UserNotFound() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(userRepository.execute(id)).thenReturn(null);

        // Act
        UserResponse actualResponse = findUserService.execute(id);

        // Assert
        assertNull(actualResponse);
        verify(userRepository, times(1)).execute(id);
    }
}
