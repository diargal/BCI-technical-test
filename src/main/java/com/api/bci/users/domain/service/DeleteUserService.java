package com.api.bci.users.domain.service;

import com.api.bci.users.domain.port.DeleteUserRepository;

import java.util.UUID;

public class DeleteUserService {
    private final DeleteUserRepository repository;

    public DeleteUserService(DeleteUserRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        repository.execute(id);
    }
}
