package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.models.Bite;
import noroff.project.hvz.services.BiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/game/{gameId}/bite")
public class BiteController {
    private final BiteService biteService;

    public BiteController(BiteService biteService){
        this.biteService=biteService;
    }

    @Operation(summary = "Returns a list of bites.")
    @GetMapping // GET: localhost:8080/api/v1/game/{gameId}/bite
    public ResponseEntity<Collection<Bite>> getAll(@PathVariable int gameId) {
        Collection<Bite> bites = biteService.findAllByGameId(gameId);
        return ResponseEntity.ok(bites);
    }

    @Operation(summary = "Returns a specific bite object.")
    @GetMapping("{id}") // GET: localhost:8080/api/v1/game/{gameId}/bite/{biteId}
    public ResponseEntity<Bite> getById(@PathVariable int id) {
        Bite bite = biteService.findById(id);
        return ResponseEntity.ok(bite);
    }

    @Operation(summary = "Creates a bite object by looking up the victim by the specified bite code.")
    @PostMapping // POST: localhost:8080/api/v1/game/{gameId}/bite/
    public ResponseEntity<?> add(@RequestBody Bite bite) {
        biteService.add(bite);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Updates a bite object. Admin or biter only.")
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/game/{gameId}/bite/{biteId}
    public ResponseEntity<?> update(@RequestBody Bite bite, @PathVariable int id) {
        if (id != bite.getId())
            return ResponseEntity.badRequest().build();
        biteService.update(bite);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a bite. Admin only.")
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/game/{gameId}/bite/{biteId}
    public ResponseEntity<?> delete(@PathVariable int id) {
        biteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    /*
    GET /game/<game_id>/kill
    Get a list of kills. Optionally accepts appropriate query parameters.
    
    GET /game/<game_id>/kill/<kill_id>
    Returns a specific kill object.
    
    POST /game/<game_id>/kill
    Creates a kill object by looking up the victim by the specified bite code. Accepts
    appropriate parameters in the request body as application/json. Returns 401 Bad
    Request if the user is not an administrator and the specified bite code is invalid.
    
    PUT /game/<game_id>/kill/<kill_id>
    Updates a kill object. Accepts appropriate parameters in the request body as application/json.
    Only the killer or an administrator may update a kill object.
    
    DELETE /game/<game_id>/kill/<kill_id>
    Delete a kill. Admin only
     */
}
