package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.mappers.ChatMessageMapper;
import noroff.project.hvz.mappers.SquadMapper;
import noroff.project.hvz.mappers.SquadMemberMapper;
import noroff.project.hvz.models.Squad;
import noroff.project.hvz.models.SquadCheckin;
import noroff.project.hvz.models.dtos.ChatMessageGetDto;
import noroff.project.hvz.models.dtos.ChatMessageSquadPostDto;
import noroff.project.hvz.models.dtos.SquadGetDto;
import noroff.project.hvz.models.dtos.SquadPostDto;
import noroff.project.hvz.services.ChatMessageService;
import noroff.project.hvz.services.SquadCheckinService;
import noroff.project.hvz.services.SquadMemberService;
import noroff.project.hvz.services.SquadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/game/{gameId}/squad")
public class SquadController {
    private final SquadService squadService;
    private final SquadMemberService squadMemberService;
    private final ChatMessageService chatMessageService;
    private final SquadCheckinService squadCheckinService;
    private final SquadMapper squadMapper;
    private final ChatMessageMapper chatMessageMapper;
    private final SquadMemberMapper squadMemberMapper;

    public SquadController(SquadService squadService, SquadMemberService squadMemberService, SquadMemberMapper squadMemberMapper, SquadCheckinService squadCheckinService, ChatMessageService chatMessageService, SquadMapper squadMapper, ChatMessageMapper chatMessageMapper){
        this.squadService=squadService;
        this.squadMemberService=squadMemberService;
        this.squadMemberMapper=squadMemberMapper;
        this.squadCheckinService=squadCheckinService;
        this.chatMessageService=chatMessageService;
        this.squadMapper = squadMapper;
        this.chatMessageMapper = chatMessageMapper;
    }

    @Operation(summary = "Returns a list of squads.")
    @GetMapping // GET: localhost:8080/api/v1/game/{gameId}/squad
    public ResponseEntity<Collection<Squad>> getAll(@PathVariable int gameId) {
        Collection<Squad> squads = squadService.findAllByGameId(gameId);
        return ResponseEntity.ok(squads);
    }

    @Operation(summary = "Returns a specific squad object.")
    @GetMapping("{squadId}") // GET: localhost:8080/api/v1/game/{gameId}/squad/{squadId}
    public ResponseEntity<SquadGetDto> getById(@PathVariable int squadId) {
        SquadGetDto squad = squadMapper.toSquadDto(squadService.findById(squadId));
        return ResponseEntity.ok(squad);
    }

    @Operation(summary = "Creates a squad member object (join a squad).")
    @PostMapping ("{squadId}/join")// POST: localhost:8080/api/v1/game/{gameId}/squad/<squadId>/join
    public ResponseEntity<?> join(@PathVariable int gameId, @PathVariable int squadId, @RequestHeader("player-id") int playerId) {
        squadMemberService.joinSquad(squadId, playerId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Deletes a squad member object (leave a squad).")
    @DeleteMapping ("leave")// DELETE: localhost:8080/api/v1/game/{gameId}/squad/leave
    public ResponseEntity<?> leave(@PathVariable int gameId, @RequestHeader("player-id") int playerId) {
        squadMemberService.leaveSquad(playerId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Creates a squad object.")
    @PostMapping // POST: localhost:8080/api/v1/game/{gameId}/squad/
    public ResponseEntity<?> add(@RequestBody SquadPostDto squad) {
        squadService.add(squadMapper.toSquad(squad));
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
    @GetMapping("{id}/chat") // GET: localhost:8080/api/v1/game/<game_id>/squad/<squad_id>/chat
    public ResponseEntity<List<ChatMessageGetDto>> getChatById(@PathVariable int id) {
        List<ChatMessageGetDto> chatMessages = chatMessageService.findAllBySquadId(id);
        return ResponseEntity.ok(chatMessages);
    }

    @Operation(summary = "Creates a new squad chat message.")
    @PostMapping("{squadId}/chat") // POST: localhost:8080/api/v1/game/<game_id>/chat
    public ResponseEntity<?> add(@PathVariable int gameId, @PathVariable int squadId, @RequestHeader("player-id") int playerId, @RequestBody ChatMessageSquadPostDto chatMessage) {
        chatMessageService.addSquadChat(chatMessageMapper.toSquadChatMessage(chatMessage, gameId, playerId),squadId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get a list of squad check-in markers.")
    @GetMapping("{id}/check-in") // GET: localhost:8080/api/v1/game/<game_id>/squad/<squad_id>/check-in
    public ResponseEntity<List<SquadCheckin>> getCheckinById(@PathVariable int id) {
        List<SquadCheckin> squadCheckins = squadService.findById(id).getSquadCheckins();
        return ResponseEntity.ok(squadCheckins);
    }

    @Operation(summary = "Create a squad checkin.")
    @PostMapping("{id}/check-in") // POST: localhost:8080/api/v1/game/<game_id>/squad/<squad_id>/check-in
    public ResponseEntity<?> addCheckin(@RequestBody SquadCheckin squadCheckin) {
        squadCheckinService.add(squadCheckin);
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
