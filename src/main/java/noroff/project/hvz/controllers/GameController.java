package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.mappers.ChatMessageMapper;
import noroff.project.hvz.mappers.GameMapper;
import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.*;
import noroff.project.hvz.services.ChatMessageService;
import noroff.project.hvz.services.GameService;
import noroff.project.hvz.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        this.gameMapper = gameMapper;
        this.chatMessageMapper = chatMessageMapper;
    }

    @Operation(summary = "Return a list of games")
    @GetMapping // GET: localhost:8080/api/v1/game
    public ResponseEntity<Collection<GameGetDto>> getAll() {
        List<GameGetDto> games = gameMapper.toGameDto(gameService.findAll());
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Return a specific game object")
    @GetMapping("{gameId}") // GET: localhost:8080/api/v1/game
    public ResponseEntity<GameGetDto> getById(@PathVariable int gameId) {
        GameGetDto game = gameMapper.toGameDto(gameService.findById(gameId));
        return ResponseEntity.ok(game);
    }

    @Operation(summary = "Create a new game. Admin only")
    @PostMapping // POST: localhost:8080/api/v1/game
    public ResponseEntity<?> add(@RequestBody GameAndCoordinatesDto g) {
        Game createdGame = gameService.createGameWithMap(gameMapper.toGame(g.getGameDto()), g.getMapCoordinateDtos());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdGame.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update a game. Admin only")
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

    @Operation(summary = "Delete (cascading) a game. Admin only")
    @DeleteMapping("{gameId}") // DELETE: localhost:8080/api/v1/game/<game_id>
    public ResponseEntity<?> delete(@PathVariable int gameId) {
        gameService.deleteById(gameId);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Return a list of faction-specific chat messages")
    @GetMapping("{gameId}/chat") // GET: localhost:8080/api/v1/game/<game_id>/chat
    public ResponseEntity<List<ChatMessageGetDto>> getChatById(Principal principal, @PathVariable int gameId) {
        Player player = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        List<ChatMessageGetDto> chatMessages = chatMessageService.findAllGlobalAndPlayerFactionMessages(gameId, player);
        return ResponseEntity.ok(chatMessages);
    }

    @Operation(summary = "Create a new chat message")
    @PostMapping("{gameId}/chat") // POST: localhost:8080/api/v1/game/<game_id>/chat
    public ResponseEntity<?> add(Principal principal, @PathVariable int gameId, @RequestBody ChatMessagePostDto chatMessage) {
        Player player = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        ChatMessage chat = chatMessageService.addGlobalOrFactionChat(chatMessageMapper.toChatMessage(chatMessage, gameId, player));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(chat.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
