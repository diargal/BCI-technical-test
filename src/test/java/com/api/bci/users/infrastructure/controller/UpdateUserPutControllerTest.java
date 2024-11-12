package com.api.bci.users.infrastructure.controller;

import com.api.bci.users.BciJavaApplication;
import com.api.bci.users.domain.model.Phone;
import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.port.UpdateUserRepository;
import com.api.bci.users.domain.validation.exception.UserNotFoundException;
import com.api.bci.users.infrastructure.controller.dto.PhoneDto;
import com.api.bci.users.infrastructure.controller.dto.UserRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest(classes = BciJavaApplication.class)
class UpdateUserPutControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UpdateUserRepository updateUserRepository;

    @Test
    @WithMockUser
    void updateUser_Successful() throws Exception {
        UUID id = UUID.randomUUID();
        String name = "Jhon Doe";
        String userEmail = "john.doe.666@example.com";
        String password = "password123";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        String token = "I'm a Token";
        LocalDateTime now = LocalDateTime.now();
        Phone phone = new Phone(cellphone, cityCode, countryCode);
        List<Phone> phones = List.of(phone);
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserResponse response = new UserResponse(id, name, userEmail,
                phones, now, now, null, true, token);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);

        when(updateUserRepository.execute(any(), any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userEmail))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active").value(Boolean.TRUE))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void updateUser_EmailNotFound_ThrownException() throws Exception {
        UUID id = UUID.randomUUID();
        String name = "Jhon Doe";
        String userEmail = "john.doe.33@example.com";
        String password = "password123";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);

        when(updateUserRepository.execute(any(), any())).thenThrow(new UserNotFoundException("No se encontró registro."));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No se encontró registro."))
                .andDo(print());
    }
}