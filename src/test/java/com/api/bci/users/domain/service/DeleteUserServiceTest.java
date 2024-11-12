package com.api.bci.users.domain.service;

import com.api.bci.users.domain.port.DeleteUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteUserServiceTest {

    @Mock
    private DeleteUserRepository userRepository;

    @InjectMocks
    private DeleteUserService deleteUserService;


    @Test
    void testExecute_UserDeletedSuccessfully() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        deleteUserService.execute(id);

        // Assert
        verify(userRepository, times(1)).execute(id);
    }
}
