package com.api.bci.users.application.handler;

import com.api.bci.users.infrastructure.controller.dto.UserResponseDto;
import com.api.bci.users.infrastructure.controller.mapper.UserMapper;
import com.api.bci.users.domain.service.FindUserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FindUserHandler {
    private final FindUserService service;
    private final UserMapper mapper;

    public FindUserHandler(FindUserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public UserResponseDto execute(UUID id) {
        return mapper.responseToDto(service.execute(id));
    }
}
