package com.api.bci.users.infrastructure.adapter.persistence;

import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.model.enums.ErrorMessageEnum;
import com.api.bci.users.domain.port.FindUserRepository;
import com.api.bci.users.domain.validation.exception.UserNotFoundException;
import com.api.bci.users.infrastructure.adapter.persistence.mapper.UserEntityMapper;
import com.api.bci.users.infrastructure.adapter.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FindUserAdapter implements FindUserRepository {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public FindUserAdapter(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserResponse execute(UUID id) {
        return userRepository.findById(id)
                .map(userEntityMapper::responseToModel)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessageEnum.USER_NOT_FOUND_EXCEPTION.getMessage()));
    }
}
