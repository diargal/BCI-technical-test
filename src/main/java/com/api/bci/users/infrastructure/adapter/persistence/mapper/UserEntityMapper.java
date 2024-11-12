package com.api.bci.users.infrastructure.adapter.persistence.mapper;

import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.infrastructure.adapter.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserEntityMapper {
    @Mapping(target = "roles", source = "roles")
    UserEntity toEntity(UserRequest userRequest);

    UserResponse responseToModel(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "created", ignore = true)
    void updateUserFromModel(UserRequest userRequest, @MappingTarget UserEntity userEntity);

}
