package noroff.project.hvz.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAll() {
    }

    @Test
    @WithMockUser(username = "admin", roles = { "hvz_admin" })
    void add_shouldCreateNewGame() throws Exception {
        this.mockMvc
                .perform(post("/api/v1/game")
                        .contentType(APPLICATION_JSON)
                        .content("""
           {
              "gameDto": {
                "name": "string",
                "description": "stringstri",
                "startDateTime": "2023-04-05T22:30:17.690Z",
                "endDateTime": "2023-04-05T22:30:17.690Z"
              },
              "mapCoordinateDtos": [
                {
                  "latitude": 0,
                  "longitude": 0
                }
              ]
            }
          """)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getChatById() {
    }

    @Test
    void testAdd() {
    }
}