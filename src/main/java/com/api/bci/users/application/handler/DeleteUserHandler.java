package com.api.bci.users.application.handler;

import com.api.bci.users.domain.service.DeleteUserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteUserHandler {
    private final DeleteUserService service;

    public DeleteUserHandler(DeleteUserService service) {
        this.service = service;
    }

    public void execute(UUID id) {
        service.execute(id);
    }
}
