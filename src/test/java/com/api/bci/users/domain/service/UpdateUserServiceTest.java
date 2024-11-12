package com.api.bci.users.domain.service;

import com.api.bci.users.domain.model.Phone;
import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.port.UpdateUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserServiceTest {

    @Mock
    private UpdateUserRepository userRepository;

    @InjectMocks
    private UpdateUserService updateUserService;


    @Test
    void testExecute_UserUpdatedSuccessfully() {
        // Arrange
        UUID id = UUID.randomUUID();
        String name = "Jhon Doe";
        String userEmail = "john.doe@example.com";
        String password = "password123";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        LocalDateTime now = LocalDateTime.now();
        Phone phone = new Phone(cellphone, cityCode, countryCode);
        List<Phone> phones = List.of(phone);
        UserRequest userRequest = new UserRequest(name, userEmail, password, phones);
        UserResponse expectedResponse = new UserResponse(id, name, userEmail,
                phones, now, now, now, true, null);

        when(userRepository.execute(id, userRequest)).thenReturn(expectedResponse);

        // Act
        UserResponse actualResponse = updateUserService.execute(id, userRequest);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(userRepository, times(1)).execute(id, userRequest);
    }

    @Test
    void testExecute_UserUpdateFailed() {
        // Arrange
        UUID id = UUID.randomUUID();
        String name = "Jhon Doe";
        String userEmail = "john.doe@example.com";
        String password = "password123";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        Phone phone = new Phone(cellphone, cityCode, countryCode);
        List<Phone> phones = List.of(phone);
        UserRequest userRequest = new UserRequest(name, userEmail, password, phones);
        when(userRepository.execute(id, userRequest)).thenReturn(null);

        // Act
        UserResponse actualResponse = updateUserService.execute(id, userRequest);

        // Assert
        assertNull(actualResponse);
        verify(userRepository, times(1)).execute(id, userRequest);
    }
}
