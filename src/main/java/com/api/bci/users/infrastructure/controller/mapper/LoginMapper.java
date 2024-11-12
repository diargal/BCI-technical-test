package com.api.bci.users.infrastructure.controller.mapper;

import com.api.bci.users.infrastructure.controller.dto.LoginRequestDto;
import com.api.bci.users.infrastructure.controller.dto.LoginResponseDto;
import com.api.bci.users.domain.model.LoginRequest;
import com.api.bci.users.domain.model.LoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LoginMapper {
    LoginRequest requestToModel(LoginRequestDto loginRequestDto);

    LoginResponseDto responseToDto(LoginResponse response);

}
