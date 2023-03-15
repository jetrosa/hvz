package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.models.Squad;
import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.services.ChatMessageService;
import noroff.project.hvz.services.SquadMemberService;
import noroff.project.hvz.services.SquadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/game/{gameId}/squad")
public class SquadController {
    private final SquadService squadService;
    private final SquadMemberService squadMemberService;
    private final ChatMessageService chatMessageService;

    public SquadController(SquadService squadService, SquadMemberService squadMemberService, ChatMessageService chatMessageService){
        this.squadService=squadService;
        this.squadMemberService=squadMemberService;
        this.chatMessageService=chatMessageService;
    }

    @Operation(summary = "Returns a list of squads.")
    @GetMapping // GET: localhost:8080/api/v1/game/{gameId}/squad
    public ResponseEntity<Collection<Squad>> getAll(@PathVariable int gameId) {
        Collection<Squad> squads = squadService.findAllByGameId(gameId);
        return ResponseEntity.ok(squads);
    }

    @Operation(summary = "Returns a specific squad object.")
    @GetMapping("{id}") // GET: localhost:8080/api/v1/game/{gameId}/squad/{squadId}
    public ResponseEntity<Squad> getById(@PathVariable int id) {
        Squad squad = squadService.findById(id);
        return ResponseEntity.ok(squad);
    }

    @Operation(summary = "Creates a squad member object.")
    @PostMapping ("{squad_id}/join")// POST: localhost:8080/api/v1/game/{gameId}/squad/<squad_id>/join
    public ResponseEntity<?> join(@RequestBody SquadMember squadMember) {
        squadMemberService.add(squadMember);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Creates a squad object.")
    @PostMapping // POST: localhost:8080/api/v1/game/{gameId}/squad/
    public ResponseEntity<?> add(@RequestBody Squad squad) {
        squadService.add(squad);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Updates a squad object. Admin only.")
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/game/{gameId}/squad/{squadId}
    public ResponseEntity<?> update(@RequestBody Squad squad, @PathVariable int id) {
        if (id != squad.getId())
            return ResponseEntity.badRequest().build();
        squadService.update(squad);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a squad. Admin only.")
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/game/{gameId}/squad/{squadId}
    public ResponseEntity<?> delete(@PathVariable int id) {
        squadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Returns a list of squad chat messages.")
    @GetMapping("{id}/chat") // GET: localhost:8080/api/v1/game/<game_id>/chat
    public ResponseEntity<Set<ChatMessage>> getChatById(@PathVariable int id) {
        Set<ChatMessage> chatMessages = chatMessageService.findAllBySquadId(id);
        return ResponseEntity.ok(chatMessages);
    }

    @Operation(summary = "Creates a new squad chat message.")
    @PostMapping("{id}/chat") // POST: localhost:8080/api/v1/game/<game_id>/chat
    public ResponseEntity<?> add(@RequestBody ChatMessage chatMessage) {
        chatMessageService.add(chatMessage);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    /*
    GET /game/<game_id>/squad
    Get a list of squads. Optionally accepts appropriate query parameters.

    GET /game/<game_id>/squad/<squad_id>
    Returns a specific squad object.

    POST /game/<game_id>/squad
    Creates a squad object. Accepts appropriate parameters in the request body as
    application/json. Should also automatically create a corresponding squad member
    object that registers the player as the ranking member of the squad they just created.

    POST /game/<game_id>/squad/<squad_id>/join
    Creates a squad member object. Accepts appropriate parameters in the request body
    as application/json.

    PUT /game/<game_id>/squad/<squad_id>
    Updates a squad object. Accepts appropriate parameters in the request body as
    application/json. Admin only.

    DELETE /game/<game_id>/squad/<squad_id>
    Delete a squad. Admin only.

    GET /game/<game_id>/squad/<squad_id>/chat
    Returns a list of chat messages. Optionally accepts appropriate query parameters.
    The messages returned should take into account the current game state of the player,
    i.e. a human should recieve chat messages addressed to the ”global” (cross-faction chat)
    and ”human” chats but not the ”zombie” chat.

    POST /game/<game_id>/squad/<squad_id>/chat
    Send a new chat message addressed to a particular squad. Accepts appropriate pa-
    rameters in the request body as application/json. Only administrators and members
    of a squad who are still in the appropriate faction may send messages to the squad chat,
    i.e. a human who has died should not be able to continue sending messages to their
    human squad; in this event it returns 403 Forbidden.

    GET /game/<game_id>/squad/<squad_id>/check-in
    Get a list of squad check-in markers. Optionally accepts appropriate query parameters.
    Only administrators and members of a squad who are still in the appropriate faction
    may see squad check-ins, i.e. a human who has died should not be able to access the
    check-ins of their human squad; in this event it returns 403 Forbidden.

    POST /game/<game_id>/squad/<squad_id>/check-in
    Create a squad checkin. Accepts appropriate parameters in the request body as
    application/json. Only members of a squad who are still in the appropriate fac-
    tion may check-in with their squad, i.e. a human who has died should not be able to
    access the check-ins of their human squad; in this event it returns 403 Forbidden.
     */
}
