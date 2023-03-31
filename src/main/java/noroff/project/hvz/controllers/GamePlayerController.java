package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.PlayerUpdateDto;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadDto;
import noroff.project.hvz.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/game/{gameId}/player")
public class GamePlayerController {
    private final PlayerService playerService;

    public GamePlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @Operation(summary = "Get a list of players")
    @GetMapping // GET: localhost:8080/api/v1/game/{gameId}/player
    public ResponseEntity<Collection<Player>> getAll(@PathVariable int gameId) {
        Collection<Player> players = playerService.findAllByGameId(gameId);
        return ResponseEntity.ok(players);
    }

    @Operation(summary = "Return a specific player object")
    @GetMapping("{playerId}") // GET: localhost:8080/api/v1/game/{gameId}/player/{playerId}
    public ResponseEntity<PlayerWithNameAndSquadDto> getById(@PathVariable int playerId) {
        PlayerWithNameAndSquadDto player = playerService.findPlayerWithNameAndSquadById(playerId);
        return ResponseEntity.ok(player);
    }

    @Operation(summary = "Create a new player")
    @PostMapping // POST: localhost:8080/api/v1/game/{gameId}/player/
    public ResponseEntity<?> add(Principal principal, @PathVariable int gameId) {
        Player player = playerService.addWithDefaultValues(principal.getName(), gameId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(player.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update a player. Admin only")
    @PutMapping("{playerId}") // PUT: localhost:8080/api/v1/game/{gameId}/player/{playerId}
    public ResponseEntity<?> update(@RequestBody PlayerUpdateDto player, @PathVariable int playerId) {
        playerService.updateWithDto(player, playerId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete (cascading) a player. Admin only")
    @DeleteMapping("{playerId}") // DELETE: localhost:8080/api/v1/game/{gameId}/player/{playerId}
    public ResponseEntity<?> delete(@PathVariable int playerId) {
        playerService.deleteById(playerId);
        return ResponseEntity.noContent().build();
    }
}
