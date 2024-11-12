package com.api.bci.users.infrastructure.adapter.persistence;

import com.api.bci.users.domain.model.enums.ErrorMessageEnum;
import com.api.bci.users.domain.port.DeleteUserRepository;
import com.api.bci.users.domain.validation.exception.UserNotFoundException;
import com.api.bci.users.infrastructure.adapter.persistence.entity.UserEntity;
import com.api.bci.users.infrastructure.adapter.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class DeleteUserAdapter implements DeleteUserRepository {
    private final UserRepository userRepository;

    public DeleteUserAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(UUID id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            userRepository.delete(userEntity.get());
        } else {
            throw new UserNotFoundException(ErrorMessageEnum.USER_NOT_FOUND_EXCEPTION.getMessage());
        }
    }
}
