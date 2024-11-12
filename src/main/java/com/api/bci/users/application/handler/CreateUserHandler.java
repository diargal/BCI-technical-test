package com.api.bci.users.application.handler;

import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.service.CreateUserService;
import com.api.bci.users.infrastructure.controller.dto.UserRequestDto;
import com.api.bci.users.infrastructure.controller.dto.UserResponseDto;
import com.api.bci.users.infrastructure.controller.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateUserHandler {
    private final CreateUserService service;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public CreateUserHandler(CreateUserService service, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto execute(UserRequestDto userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        UserRequest userRequest = mapper.requestToModel(userRequestDto);
        return mapper.responseToDto(service.execute(userRequest));
    }
}
