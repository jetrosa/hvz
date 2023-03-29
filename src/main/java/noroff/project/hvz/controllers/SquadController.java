package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.customexceptions.InvalidSquadException;
import noroff.project.hvz.mappers.ChatMessageMapper;
import noroff.project.hvz.mappers.SquadCheckinMapper;
import noroff.project.hvz.mappers.SquadMapper;
import noroff.project.hvz.models.*;
import noroff.project.hvz.models.dtos.*;
import noroff.project.hvz.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
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
    private final SquadCheckinMapper squadCheckinMapper;
    private final PlayerService playerService;

    public SquadController(SquadService squadService, SquadMemberService squadMemberService,
                           SquadCheckinService squadCheckinService, ChatMessageService chatMessageService,
                           SquadMapper squadMapper, SquadCheckinMapper squadCheckinMapper, ChatMessageMapper chatMessageMapper, PlayerService playerService) {
        this.squadService = squadService;
        this.squadMemberService = squadMemberService;
        this.squadCheckinService = squadCheckinService;
        this.chatMessageService = chatMessageService;
        this.squadMapper = squadMapper;
        this.squadCheckinMapper = squadCheckinMapper;
        this.chatMessageMapper = chatMessageMapper;
        this.playerService = playerService;
    }

    @Operation(summary = "Returns a list of squads.")
    @GetMapping // GET: localhost:8080/api/v1/game/{gameId}/squad
    public ResponseEntity<Collection<SquadGetDto>> getAll(@PathVariable int gameId) {
        Collection<SquadGetDto> squads = squadMapper.toSquadDto(squadService.findAllByGameId(gameId));
        return ResponseEntity.ok(squads);
    }

    @Operation(summary = "Returns a specific squad object.")
    @GetMapping("{squadId}") // GET: localhost:8080/api/v1/game/{gameId}/squad/{squadId}
    public ResponseEntity<SquadGetDto> getById(@PathVariable int squadId) {
        SquadGetDto squad = squadMapper.toSquadDto(squadService.findById(squadId));
        return ResponseEntity.ok(squad);
    }

    @Operation(summary = "Creates a squad member object (join a squad).")
    @PostMapping("{squadId}/join")// POST: localhost:8080/api/v1/game/{gameId}/squad/<squadId>/join
    public ResponseEntity<?> join(Principal principal, @PathVariable int gameId, @PathVariable int squadId) {
        Player player = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        squadMemberService.joinSquad(squadId, player);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Deletes a squad member object (leave a squad).")
    @DeleteMapping("leave")// DELETE: localhost:8080/api/v1/game/{gameId}/squad/leave
    public ResponseEntity<?> leave(Principal principal, @PathVariable int gameId) {
        Player player = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        squadMemberService.leaveSquad(player.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Creates a squad object.")
    @PostMapping // POST: localhost:8080/api/v1/game/{gameId}/squad
    public ResponseEntity<?> add(Principal principal, @PathVariable int gameId, @RequestBody SquadPostDto squad) {
        Player player = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        Squad createdSquad = squadMemberService.createAndJoin(squadMapper.toSquad(squad, gameId), player);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdSquad.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a squad object. Admin only.")
    @PutMapping("{squadId}") // PUT: localhost:8080/api/v1/game/{gameId}/squad/{squadId}
    public ResponseEntity<?> update(@RequestBody SquadPostDto squad, @PathVariable int squadId) {
        squadService.updateKeepRelations(squadMapper.toSquad(squad, squadId));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a squad. Admin only.")
    @DeleteMapping("{squadId}") // DELETE: localhost:8080/api/v1/game/{gameId}/squad/{squadId}
    public ResponseEntity<?> delete(@PathVariable int squadId) {
        squadService.deleteById(squadId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Returns a list of squad chat messages.")
    @GetMapping("{squadId}/chat") // GET: localhost:8080/api/v1/game/<game_id>/squad/<squad_id>/chat
    public ResponseEntity<List<ChatMessageGetDto>> getChatById(Principal principal, @PathVariable int gameId, @PathVariable int squadId) {
        Player player = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        SquadMember member = squadMemberService.findByPlayerId(player.getId());
        if(member==null||player.getId()!= member.getPlayer().getId()){
            throw new InvalidSquadException(player.getId(), squadId);
        }
        List<ChatMessageGetDto> chatMessages = chatMessageService.findAllBySquadId(squadId);
        return ResponseEntity.ok(chatMessages);
    }

    @Operation(summary = "Creates a new squad chat message.")
    @PostMapping("{squadId}/chat") // POST: localhost:8080/api/v1/game/<game_id>/chat
    public ResponseEntity<?> add(Principal principal, @PathVariable int gameId, @PathVariable int squadId, @RequestBody ChatMessageSquadPostDto chatMessage) {
        Player player = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        ChatMessage createdChat = chatMessageService.addSquadChat(chatMessageMapper.toSquadChatMessage(chatMessage, gameId, player), squadId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdChat.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Get a list of squad check-in markers.")
    @GetMapping("{squadId}/check-in") // GET: localhost:8080/api/v1/game/<game_id>/squad/<squad_id>/check-in
    public ResponseEntity<List<SquadCheckinGetDto>> getCheckinBySquadId(@PathVariable int squadId) {
        List<SquadCheckinGetDto> squadCheckins = squadCheckinMapper.toSquadCheckinDto(squadService.findById(squadId).getSquadCheckins());
        return ResponseEntity.ok(squadCheckins);
    }

    @Operation(summary = "Create a squad checkin.")
    @PostMapping("{squadId}/check-in") // POST: localhost:8080/api/v1/game/<game_id>/squad/<squad_id>/check-in
    public ResponseEntity<?> addSquadCheckin(Principal principal, @PathVariable int gameId, @PathVariable int squadId, @RequestBody SquadCheckinPostDto squadCheckin) {
        Player player = playerService.findByGameIdAndAppUserUuid(gameId, principal.getName());
        SquadMember s = squadMemberService.findByPlayerId(player.getId());
        if(s.getSquad().getId()!=squadId)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        SquadCheckin createdSquadCheckin = squadCheckinService.addOrUpdate(squadCheckinMapper.toSquadCheckin(squadCheckin,gameId, squadId, player)) ;
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdSquadCheckin.getId())
                .toUri();

        return ResponseEntity.created(location).build();
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
