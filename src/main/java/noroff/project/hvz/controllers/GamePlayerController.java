package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadDto;
import noroff.project.hvz.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/game/{gameId}/player")
public class GamePlayerController {
    private final PlayerService playerService;

    public GamePlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @Operation(summary = "Get a list of players.")
    @GetMapping // GET: localhost:8080/api/v1/game/{gameId}/player
    public ResponseEntity<Collection<Player>> getAll(@PathVariable int gameId) {
        Collection<Player> players = playerService.findAllByGameId(gameId);
        return ResponseEntity.ok(players);
    }

    @Operation(summary = "Returns a specific player object.")
    @GetMapping("{playerId}") // GET: localhost:8080/api/v1/game/{gameId}/player/{playerId}
    public ResponseEntity<PlayerWithNameAndSquadDto> getById(@PathVariable int playerId) {
        PlayerWithNameAndSquadDto player = playerService.findPlayerWithNameAndSquadById(playerId);
        return ResponseEntity.ok(player);
    }

    @Operation(summary = "Creates a new player.")
    @PostMapping // POST: localhost:8080/api/v1/game/{gameId}/player/
    public ResponseEntity<?> add(@PathVariable int gameId, @RequestHeader("keycloak_player_uuid") String userUuid) {
        playerService.addWithDefaultValues(userUuid,gameId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Updates a player. Admin only.")
    @PutMapping("{playerId}") // PUT: localhost:8080/api/v1/game/{gameId}/player/{playerId}
    public ResponseEntity<?> update(@RequestBody Player player, @PathVariable int playerId) {
        if (playerId != player.getId())
            return ResponseEntity.badRequest().build();
        playerService.update(player);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes (cascading) a player. Admin only.")
    @DeleteMapping("{playerId}") // DELETE: localhost:8080/api/v1/game/{gameId}/player/{playerId}
    public ResponseEntity<?> delete(@PathVariable int playerId) {
        playerService.deleteById(playerId);
        return ResponseEntity.noContent().build();
    }

    /*
    3.3.4. API-04: Player
GET /game/<game_id>/player
Get a list of players. Player details should be not expose sensitive information such
as the player’s is_patient_zero flag when being accessed by a non-administrator. Op-
tionally accepts appropriate query parameters.
GET /game/<game_id>/player/<player_id>
Returns a specific player object. Player details should be not expose sensitive in-
formation such as the player’s is_patient_zero flag when being accessed by a non-
administrator.
POST /game/<game_id>/player
Registers a user for a game. Player objects must be unique per user, per game. Only
administrators may specify appropriate parameters in the request body as application/json
– otherwise all parameters assume default values.
PUT /game/<game_id>/player/<player_id>
Updates a player object. Accepts appropriate parameters in the request body as
application/json. Admin only.
DELETE /game/<game_id>/player/<player_id>
Deletes (cascading) a player. Admin only.
     */
}
