package com.api.bci.users.domain.service;

import com.api.bci.users.domain.model.LoginRequest;
import com.api.bci.users.domain.model.LoginResponse;
import com.api.bci.users.domain.model.Phone;
import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.port.AuthRepository;
import com.api.bci.users.domain.port.CreateUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @Mock
    private CreateUserRepository userRepository;

    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private CreateUserService createUserService;

    @Test
    void testExecute_createUserSuccessfully() {
        //Arrange
        String name = "Jhon Doe";
        String userEmail = "john.doe@example.com";
        String password = "password123";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        String token = "token123";
        LocalDateTime now = LocalDateTime.now();
        Phone phone = new Phone(cellphone, cityCode, countryCode);
        List<Phone> phones = List.of(phone);
        UUID id = UUID.randomUUID();
        UserRequest userRequest = new UserRequest(name, userEmail, password, phones);
        UserResponse expectedResponse = new UserResponse(id, name, userEmail,
                phones, now, now, now, true, null);
        LoginRequest loginRequest = new LoginRequest(userEmail, password);
        LoginResponse loginResponse = new LoginResponse(token);

        when(userRepository.execute(userRequest)).thenReturn(expectedResponse);
        when(authRepository.execute(loginRequest)).thenReturn(loginResponse);

        // Act
        UserResponse result = createUserService.execute(userRequest);

        // Assert
        assertEquals(id, result.getId());
        assertEquals(userEmail, result.getEmail());
        assertEquals(now, result.getCreated());
        assertEquals(now, result.getModified());
        assertEquals(now, result.getLastLogin());
        assertTrue(result.isActive());
        assertEquals(token, result.getToken());
        verify(userRepository, times(1)).execute(userRequest);
        verify(authRepository, times(1)).execute(loginRequest);
    }
}
