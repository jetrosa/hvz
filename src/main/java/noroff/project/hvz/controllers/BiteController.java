package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.mappers.BiteMapper;
import noroff.project.hvz.models.Bite;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.BiteGetDto;
import noroff.project.hvz.models.dtos.BitePostDto;
import noroff.project.hvz.models.dtos.BiteUpdateDto;
import noroff.project.hvz.services.BiteService;
import noroff.project.hvz.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/game/{gameId}/bite")
public class BiteController {
    private final BiteService biteService;
    private final BiteMapper biteMapper;
    private final PlayerService playerService;

    public BiteController(BiteService biteService, BiteMapper biteMapper, PlayerService playerService) {
        this.biteService = biteService;
        this.biteMapper = biteMapper;
        this.playerService = playerService;
    }

    @Operation(summary = "Return a list of bites")
    @GetMapping // GET: localhost:8080/api/v1/game/{gameId}/bite
    public ResponseEntity<List<BiteGetDto>> getAll(@PathVariable int gameId) {
        List<BiteGetDto> bites = biteMapper.toBiteDto(biteService.findAllByGameId(gameId));
        return ResponseEntity.ok(bites);
    }

    @Operation(summary = "Return a specific bite object")
    @GetMapping("{biteId}") // GET: localhost:8080/api/v1/game/{gameId}/bite/{biteId}
    public ResponseEntity<BiteGetDto> getById(@PathVariable int biteId) {
        BiteGetDto bite = biteMapper.toBiteDto(biteService.findById(biteId));
        return ResponseEntity.ok(bite);
    }

    @Operation(summary = "Create a bite object by looking up the victim by the specified bite code")
    @PostMapping // POST: localhost:8080/api/v1/game/{gameId}/bite/
    public ResponseEntity<?> add(Principal principal, @PathVariable int gameId, @RequestBody BitePostDto bite) {
        Player biter = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        Bite createdBite = biteService.add(biteMapper.toBitePost(bite, gameId, biter));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBite.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update a bite object. Admin only")
    @PutMapping("{biteId}") // PUT: localhost:8080/api/v1/game/{gameId}/bite/{biteId}
    public ResponseEntity<?> update(@PathVariable int biteId, @PathVariable int gameId, @RequestBody BiteUpdateDto bite) {
        biteService.update(biteMapper.toBiteUpdate(bite, gameId, biteId));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a bite. Admin only")
    @DeleteMapping("{biteId}") // DELETE: localhost:8080/api/v1/game/{gameId}/bite/{biteId}
    public ResponseEntity<?> delete(@PathVariable int biteId) {
        biteService.deleteById(biteId);
        return ResponseEntity.noContent().build();
    }
}
