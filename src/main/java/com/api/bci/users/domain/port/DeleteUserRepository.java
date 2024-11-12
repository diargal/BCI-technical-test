package com.api.bci.users.domain.port;

import java.util.UUID;

@FunctionalInterface
public interface DeleteUserRepository {
    void execute(UUID id);
}
