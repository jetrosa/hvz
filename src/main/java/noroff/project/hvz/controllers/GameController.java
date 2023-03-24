package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.mappers.ChatMessageMapper;
import noroff.project.hvz.mappers.GameMapper;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.*;
import noroff.project.hvz.services.ChatMessageService;
import noroff.project.hvz.services.GameService;
import noroff.project.hvz.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/game")
public class GameController {
    private final GameService gameService;
    private final ChatMessageService chatMessageService;
    private final PlayerService playerService;
    private final GameMapper gameMapper;
    private final ChatMessageMapper chatMessageMapper;

    public GameController(GameService gameService, ChatMessageService chatMessageService, PlayerService playerService, GameMapper gameMapper, ChatMessageMapper chatMessageMapper) {
        this.gameService = gameService;
        this.chatMessageService = chatMessageService;
        this.playerService = playerService;
        this.gameMapper=gameMapper;
        this.chatMessageMapper = chatMessageMapper;
    }

    @Operation(summary = "Returns a list of games.")
    @GetMapping // GET: localhost:8080/api/v1/game
    public ResponseEntity<Collection<GameGetDto>> getAll() {
        List<GameGetDto> games = gameMapper.toGameDto(gameService.findAll());
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Returns a specific game object.")
    @GetMapping("{gameId}") // GET: localhost:8080/api/v1/game
    public ResponseEntity<GameGetDto> getById(@PathVariable int gameId) {
        GameGetDto game = gameMapper.toGameDto(gameService.findById(gameId));
        return ResponseEntity.ok(game);
    }

    @Operation(summary = "Creates a new game. Admin only.")
    @PostMapping // POST: localhost:8080/api/v1/game
    public ResponseEntity<?> add(@RequestBody GameAndCoordinatesDto g) {
        gameService.createGameWithMap(gameMapper.toGame(g.getGameDto()), g.getMapCoordinateDtos());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Updates a game. Admin only.")
    @PutMapping("{gameId}") // PUT: localhost:8080/api/v1/game/<game_id>
    public ResponseEntity<?> update(@RequestBody GamePostDto game, @PathVariable int gameId) {
        Game g = gameService.findById(gameId);
        g.setName(game.getName());
        g.setDescription(game.getDescription());
        g.setStartDateTime(game.getStartDateTime());
        g.setEndDateTime(game.getEndDateTime());
        gameService.update(g);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes (cascading) a game. Admin only.")
    @DeleteMapping("{gameId}") // DELETE: localhost:8080/api/v1/game/<game_id>
    public ResponseEntity<?> delete(@PathVariable int gameId) {
        gameService.deleteById(gameId);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Returns a list of faction-specific chat messages.")
    @GetMapping("{gameId}/chat") // GET: localhost:8080/api/v1/game/<game_id>/chat
    public ResponseEntity<List<ChatMessageGetDto>> getChatById(Principal principal, @PathVariable int gameId) {
        Player player = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        List<ChatMessageGetDto> chatMessages = chatMessageService.findAllGlobalAndPlayerFactionMessages(gameId, player);
        return ResponseEntity.ok(chatMessages);
    }

    @Operation(summary = "Creates a new chat message.")
    @PostMapping("{gameId}/chat") // POST: localhost:8080/api/v1/game/<game_id>/chat
    public ResponseEntity<?> add(Principal principal, @PathVariable int gameId, @RequestBody ChatMessagePostDto chatMessage) {
        Player player = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        chatMessageService.add(chatMessageMapper.toChatMessage(chatMessage, gameId, player));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    /*


GET /game/<game_id>/chat
Returns a list of chat messages. Optionally accepts appropriate query parameters.
The messages returned should take into account the current game state of the player,
i.e. a human should recieve chat messages addressed to the ”global” (cross-faction chat)
and ”human” chats but not the ”zombie” chat.
POST /game/<game_id>/chat
Send a new chat message. Accepts appropriate parameters in the request body as
application/json
     */

}
