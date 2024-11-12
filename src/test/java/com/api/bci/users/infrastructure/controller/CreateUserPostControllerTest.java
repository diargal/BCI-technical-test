package com.api.bci.users.infrastructure.controller;

import com.api.bci.users.BciJavaApplication;
import com.api.bci.users.domain.model.LoginResponse;
import com.api.bci.users.domain.model.Phone;
import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.port.AuthRepository;
import com.api.bci.users.domain.port.CreateUserRepository;
import com.api.bci.users.domain.validation.exception.ExistEmailException;
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
class CreateUserPostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CreateUserRepository createUserRepository;
    @MockBean
    private AuthRepository authRepository;

    @Test
    @WithMockUser
    void createUser_Successful() throws Exception {
        UUID id = UUID.randomUUID();
        String name = "Jhon Doe";
        String userEmail = "john.doe.888@example.com";
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
        LoginResponse loginResponse = new LoginResponse(token);

        when(createUserRepository.execute(any())).thenReturn(response);
        when(authRepository.execute(any())).thenReturn(loginResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userEmail))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isactive").value(Boolean.TRUE))
                .andDo(print());
    }

    @Test
    void createUser_EmailExists_ThrownException() throws Exception {
        String name = "Diego Garcia";
        String userEmail = "email_exist@gmail.com";
        String password = "password123";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        LocalDateTime now = LocalDateTime.now();
        Phone phone = new Phone(cellphone, cityCode, countryCode);
        List<Phone> phones = List.of(phone);
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);
        String emailErrorMessage = "El email ya se encuentra registrado.";

        when(createUserRepository.execute(any())).thenThrow(new ExistEmailException(emailErrorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(emailErrorMessage))
                .andDo(print());
    }

    @Test
    void createUser_EmailFormat_ThrownException() throws Exception {
        String name = "Jhon Doe";
        String userEmail = "bad_format_email";
        String password = "password123";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("No se cumple con el formato de correo."))
                .andDo(print());
    }

    @Test
    void createUser_EmailEmpty_ThrownException() throws Exception {
        String name = "Jhon Doe";
        String userEmail = "";
        String password = "password123";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Valor vacío o nulo: email"))
                .andDo(print());
    }

}
