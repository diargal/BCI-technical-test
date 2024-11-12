package com.api.bci.users.infrastructure.controller;

import com.api.bci.users.application.handler.FindUserHandler;
import com.api.bci.users.infrastructure.controller.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class FindUserGetController {

    private final FindUserHandler findUserHandler;

    public FindUserGetController(FindUserHandler findUserHandler) {
        this.findUserHandler = findUserHandler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable UUID id) {
        return ResponseEntity.ok(findUserHandler.execute(id));
    }
}
