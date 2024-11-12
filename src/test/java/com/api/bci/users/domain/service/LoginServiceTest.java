package com.api.bci.users.domain.service;

import com.api.bci.users.domain.model.LoginRequest;
import com.api.bci.users.domain.model.LoginResponse;
import com.api.bci.users.domain.port.AuthRepository;
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
class LoginServiceTest {

    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private LoginService loginService;

    @Test
    void testExecute_SuccessfulLogin() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "password");
        LoginResponse expectedResponse = new LoginResponse();
        when(authRepository.execute(loginRequest)).thenReturn(expectedResponse);

        // Act
        LoginResponse actualResponse = loginService.execute(loginRequest);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(authRepository, times(1)).execute(loginRequest);
    }

    @Test
    void testExecute_FailedLogin() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "incorrect_password");
        when(authRepository.execute(loginRequest)).thenReturn(null);

        // Act
        LoginResponse actualResponse = loginService.execute(loginRequest);

        // Assert
        assertNull(actualResponse);
        verify(authRepository, times(1)).execute(loginRequest);
    }
}
