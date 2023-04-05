package noroff.project.hvz.controllers;

import noroff.project.hvz.config.LoginAttemptService;
import noroff.project.hvz.config.PreSecurityFilter;
import noroff.project.hvz.mappers.GameMapper;
import noroff.project.hvz.services.ChatMessageService;
import noroff.project.hvz.services.GameService;
import noroff.project.hvz.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PreSecurityFilter preSecurityFilter;
    @MockBean
    private GameService gameService;
    @MockBean
    private GameMapper gameMapper;
    @MockBean
    private ChatMessageService chatMessageService;
    @MockBean
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAll() {
    }

    @Test
    void shouldCreateNewGame() throws Exception {
        this.mockMvc
                .perform(post("/api/v2/game")
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