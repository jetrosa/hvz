package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Returns a list of games.")
    @GetMapping // GET: localhost:8080/api/v1/game
    public ResponseEntity<Collection<Game>> getAll() {
        Collection<Game> games = gameService.findAll();
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Returns a specific game object.")
    @GetMapping("{id}") // GET: localhost:8080/api/v1/game
    public ResponseEntity<Game> getById(@PathVariable int id) {
        Game game = gameService.findById(id);
        return ResponseEntity.ok(game);
    }

    @Operation(summary = "Creates a new game. Admin only.")
    @PostMapping // POST: localhost:8080/api/v1/game
    public ResponseEntity<?> add(@RequestBody Game game) {
        gameService.add(game);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Updates a game. Admin only.")
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/game/1
    public ResponseEntity<?> update(@RequestBody Game game, @PathVariable int id) {
        if (id != game.getId())
            return ResponseEntity.badRequest().build();
        gameService.update(game);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes (cascading) a game. Admin only.")
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/game/1
    public ResponseEntity<?> delete(@PathVariable int id) {
        gameService.deleteById(id);
        return ResponseEntity.noContent().build();
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
