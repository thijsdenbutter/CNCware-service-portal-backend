package cncware.cncwareserviceportalbackend.controllers;

import cncware.cncwareserviceportalbackend.models.entities.User;
import cncware.cncwareserviceportalbackend.repositories.UserRepository;

import cncware.cncwareserviceportalbackend.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static cncware.cncwareserviceportalbackend.models.enums.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private int userId;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();;

        User user = new User();
        user.setEmail("integrationTestUser@cncware.nl");
        user.setPassword("SuperSecretEncodedPassword");
        user.setRole(USER);

        userId = userRepository.save(user).getId();
    }

    @Test
    void getUserById_shouldReturn200_andUserData() throws Exception {

        mockMvc.perform(
                get("/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.email").value("integrationTestUser@cncware.nl"))
                .andExpect(jsonPath("$.role").value("USER"));

        assertThat(userRepository.findById(userId).isPresent());
    }

    @Test
    void getUserById_shouldReturn404_whenUserDoesNotExist() throws Exception {

        mockMvc.perform(
                        get("/users/99999")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("User not found with id 99999"));
    }
}
