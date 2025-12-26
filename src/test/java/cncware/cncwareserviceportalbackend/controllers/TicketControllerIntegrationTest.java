package cncware.cncwareserviceportalbackend.controllers;

import cncware.cncwareserviceportalbackend.models.entities.Status;
import cncware.cncwareserviceportalbackend.models.entities.User;
import cncware.cncwareserviceportalbackend.models.enums.Role;
import cncware.cncwareserviceportalbackend.repositories.StatusRepository;
import cncware.cncwareserviceportalbackend.repositories.TicketRepository;
import cncware.cncwareserviceportalbackend.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class TicketControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private int userId;
    private int statusId;

    @BeforeEach
    void setup(){
        ticketRepository.deleteAll();
        userRepository.deleteAll();

        Status status = new Status();
        status.setLabel("OPEN");
        status = statusRepository.save(status);

        User user = new User();
        user.setEmail("test@cncware.nl");
        user.setPassword("encodedPassword");
        user.setRole(Role.USER);
        userId = userRepository.save(user).getId();

        statusId = status.getId();
    }

    @Test
    void createTicket_shouldReturn201() throws Exception {

        String json = """
                {
                "title" : "Help mijn test werkt niet",
                "description" : "Ik probeer mijn software te testen met een test ticket maar mijn test ticket komt niet aan.",
                "userId" : %d,
                "statusId": %d
                }
                """.formatted(userId, statusId);

        mockMvc.perform(
                post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Help mijn test werkt niet"))
                .andExpect(jsonPath("$.description").value("Ik probeer mijn software te testen met een test ticket maar mijn test ticket komt niet aan."))
                .andExpect(jsonPath("$.userId").value(userId));

        assertThat(ticketRepository.count()).isEqualTo(1);
        assertThat(ticketRepository.findAll().get(0).getTitle()).isEqualTo("Help mijn test werkt niet");
    }
}

