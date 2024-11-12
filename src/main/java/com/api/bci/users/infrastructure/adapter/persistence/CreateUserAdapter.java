package com.api.bci.users.infrastructure.adapter.persistence;

import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.model.enums.ErrorMessageEnum;
import com.api.bci.users.domain.port.CreateUserRepository;
import com.api.bci.users.domain.validation.exception.ExistEmailException;
import com.api.bci.users.infrastructure.adapter.persistence.entity.RoleEntity;
import com.api.bci.users.infrastructure.adapter.persistence.entity.UserEntity;
import com.api.bci.users.infrastructure.adapter.persistence.mapper.RoleMapper;
import com.api.bci.users.infrastructure.adapter.persistence.mapper.UserEntityMapper;
import com.api.bci.users.infrastructure.adapter.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CreateUserAdapter implements CreateUserRepository {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final RoleMapper mapper;

    public CreateUserAdapter(UserRepository userRepository, UserEntityMapper userEntityMapper, RoleMapper mapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UserResponse execute(UserRequest userRequest) {
        UserEntity userEntity = userEntityMapper.toEntity(userRequest);
        Set<RoleEntity> roles = userRequest.getRoles().stream()
                .map(mapper::roleEnumToRoleEntity)
                .collect(Collectors.toSet());
        userEntity.setRoles(roles);
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent())
            throw new ExistEmailException(ErrorMessageEnum.EMAIL_EXIST_EXCEPTION.getMessage());

        return userEntityMapper.responseToModel(userRepository.save(userEntity));
    }
}