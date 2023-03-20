package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.mappers.AppUserMapper;
import noroff.project.hvz.models.dtos.AppUserDto;
import noroff.project.hvz.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/auth")
public class UserController {
    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;

    public UserController(AppUserService appUserService, AppUserMapper appUserMapper){
        this.appUserService=appUserService;
        this.appUserMapper = appUserMapper;
    }
    @Operation(summary = "Creates a user.")
    @PostMapping // POST: localhost:8080/api/v1/auth
    public ResponseEntity<?> add(@RequestBody AppUserDto appUserDto) {
        if(appUserService.existsByUuid(appUserDto.getUuid()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        appUserService.add(appUserMapper.toAppUser(appUserDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
