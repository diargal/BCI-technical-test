package com.api.bci.users.application.handler;

import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.service.UpdateUserService;
import com.api.bci.users.infrastructure.controller.dto.UserRequestDto;
import com.api.bci.users.infrastructure.controller.dto.UserResponseDto;
import com.api.bci.users.infrastructure.controller.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateUserHandler {
    private final UpdateUserService service;
    private final UserMapper mapper;

    public UpdateUserHandler(UpdateUserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public UserResponseDto execute(UUID id, UserRequestDto userRequestDto) {
        UserRequest userRequest = mapper.requestToModel(userRequestDto);
        return mapper.responseToDto(service.execute(id, userRequest));
    }
}
