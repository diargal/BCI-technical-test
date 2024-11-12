package com.api.bci.users.application.handler;

import com.api.bci.users.infrastructure.controller.dto.LoginRequestDto;
import com.api.bci.users.infrastructure.controller.dto.LoginResponseDto;
import com.api.bci.users.infrastructure.controller.mapper.LoginMapper;
import com.api.bci.users.domain.service.LoginService;
import org.springframework.stereotype.Component;

@Component
public class LoginHandler {
    private final LoginMapper loginMapper;
    private final LoginService loginService;

    public LoginHandler(LoginMapper loginMapper, LoginService loginService) {
        this.loginMapper = loginMapper;
        this.loginService = loginService;
    }

    public LoginResponseDto execute(LoginRequestDto loginRequestDto) {
        return loginMapper.responseToDto(loginService.execute(loginMapper.requestToModel(loginRequestDto)));
    }
}
