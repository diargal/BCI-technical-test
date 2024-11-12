package com.api.bci.users.infrastructure.controller;

import com.api.bci.users.application.handler.UpdateUserHandler;
import com.api.bci.users.infrastructure.controller.dto.UserRequestDto;
import com.api.bci.users.infrastructure.controller.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UpdateUserPutController {

    private final UpdateUserHandler updateUserHandler;

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id,
                                                      @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(updateUserHandler.execute(id, userRequestDto));
    }
}
