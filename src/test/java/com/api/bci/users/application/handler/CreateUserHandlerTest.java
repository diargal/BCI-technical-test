package com.api.bci.users.application.handler;

import com.api.bci.users.domain.model.Phone;
import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.service.CreateUserService;
import com.api.bci.users.infrastructure.controller.dto.PhoneDto;
import com.api.bci.users.infrastructure.controller.dto.UserRequestDto;
import com.api.bci.users.infrastructure.controller.dto.UserResponseDto;
import com.api.bci.users.infrastructure.controller.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CreateUserHandlerTest {
    @Mock
    private CreateUserService createUserService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private CreateUserHandler createUserHandler;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testExecute_UserCreationSuccessful() {
        // Arrange
        UUID id = UUID.randomUUID();
        String name = "Jhon Doe";
        String userEmail = "john.doe@example.com";
        String password = "password123";
        String passwordEncode = "encode";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        LocalDateTime now = LocalDateTime.now();
        Phone phone = new Phone(cellphone, cityCode, countryCode);
        List<Phone> phones = List.of(phone);
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequest userRequest = new UserRequest(name, userEmail, password, phones);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);
        UserResponse expectedResponse = new UserResponse(id, name, userEmail,
                phones, now, now, now, true, null);
        UserResponseDto expectedResponseDto = new UserResponseDto(id, name, userEmail,
                phonesDto, now, now, now, true, null);

        Mockito.when(userMapper.requestToModel(userRequestDto)).thenReturn(userRequest);
        Mockito.when(createUserService.execute(userRequest)).thenReturn(expectedResponse);
        Mockito.when(userMapper.responseToDto(expectedResponse)).thenReturn(expectedResponseDto);
        Mockito.when(passwordEncoder.encode(password)).thenReturn(passwordEncode);

        // Act
        UserResponseDto actualResponseDto = createUserHandler.execute(userRequestDto);

        // Assert
        Assertions.assertEquals(expectedResponseDto, actualResponseDto);
        Mockito.verify(userMapper, Mockito.times(1)).requestToModel(userRequestDto);
        Mockito.verify(createUserService, Mockito.times(1)).execute(userRequest);
        Mockito.verify(userMapper, Mockito.times(1)).responseToDto(expectedResponse);
    }

}