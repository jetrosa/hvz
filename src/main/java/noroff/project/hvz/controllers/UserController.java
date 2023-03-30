package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.mappers.PlayerMapper;
import noroff.project.hvz.models.AppUser;
import noroff.project.hvz.models.dtos.PlayerGetDto;
import noroff.project.hvz.services.AppUserService;
import noroff.project.hvz.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/auth")
public class UserController {
    private final AppUserService appUserService;
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    public UserController(AppUserService appUserService, PlayerService playerService, PlayerMapper playerMapper){
        this.appUserService=appUserService;
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @Operation(summary = "Returns a list of player objects the user belongs to.")
    @GetMapping ("players")// GET: localhost:8080/api/v1/auth/players
    public ResponseEntity<Collection<PlayerGetDto>> getAllPlayerByUuid(Principal principal) {
        Collection<PlayerGetDto> players = playerMapper.toPlayerGetDto(playerService.findAllByAppUserUuid(principal.getName()));
        return ResponseEntity.ok(players);
    }

    /*
    //EXAMPLES

    @Operation(summary = "Get logged user info.")
    @GetMapping("info")
    public ResponseEntity getLoggedInUserInfo(@AuthenticationPrincipal Jwt principal) {
        Map<String, String> map = new HashMap<>();
        map.put("subject", principal.getClaimAsString("sub"));
        map.put("user_name", principal.getClaimAsString("preferred_username"));
        map.put("email", principal.getClaimAsString("email"));
        map.put("first_name", principal.getClaimAsString("given_name"));
        map.put("last_name", principal.getClaimAsString("family_name"));
        map.put("roles", String.valueOf(principal.getClaimAsStringList("roles")));
        return ResponseEntity.ok(map);
    }


    @Operation(summary = "Get token principal")
    @GetMapping("principal")
    public ResponseEntity getPrincipal(Principal user){
        return ResponseEntity.ok(user);
    }


    @Operation(summary = "Get logged user id.")
    @GetMapping("current")
    public ResponseEntity getCurrentlyLoggedInUser(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(
                appUserService.getById(
                        jwt.getClaimAsString("sub")
                )
        );
    }
     */

    @Operation(summary = "Creates a user.")
    @PostMapping("register") // POST: localhost:8080/api/v1/auth/register
    public ResponseEntity addNewUserFromJwt(@AuthenticationPrincipal Jwt jwt) {
        if(appUserService.existsByUuid(jwt.getClaimAsString("sub"))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        AppUser newUser = new AppUser();
        newUser.setUuid(jwt.getClaimAsString("sub"));
        newUser.setFirstName(jwt.getClaimAsString("given_name"));
        newUser.setLastName(jwt.getClaimAsString("family_name"));
        AppUser user = appUserService.add(newUser);
        URI uri = URI.create("api/v1/users/" + user.getUuid());
        return ResponseEntity.created(uri).build();
    }
}
