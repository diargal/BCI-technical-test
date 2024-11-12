package com.api.bci.users.domain.service;

import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.port.FindUserRepository;

import java.util.UUID;

public class FindUserService {
    private final FindUserRepository repository;

    public FindUserService(FindUserRepository repository) {
        this.repository = repository;
    }

    public UserResponse execute(UUID id) {
        return repository.execute(id);
    }
}
