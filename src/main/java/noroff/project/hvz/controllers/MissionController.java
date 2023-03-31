package noroff.project.hvz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import noroff.project.hvz.mappers.MissionMapper;
import noroff.project.hvz.models.Mission;
import noroff.project.hvz.models.dtos.MissionGetDto;
import noroff.project.hvz.models.dtos.MissionPostDto;
import noroff.project.hvz.services.MissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/game/{gameId}/mission")
public class MissionController {
    private final MissionService missionService;
    private final MissionMapper missionMapper;

    public MissionController(MissionService missionService, MissionMapper missionMapper){
        this.missionService=missionService;
        this.missionMapper=missionMapper;
    }

    @Operation(summary = "Return a list of missions")
    @GetMapping // GET: localhost:8080/api/v1/game/{gameId}/mission
    public ResponseEntity<Set<MissionGetDto>> getAll(@PathVariable int gameId) {
        Set<MissionGetDto> missions = missionMapper.toMissionDto(missionService.findAllByGameId(gameId));
        return ResponseEntity.ok(missions);
    }

    @Operation(summary = "Return a specific mission object")
    @GetMapping("{missionId}") // GET: localhost:8080/api/v1/game/{gameId}/mission/{missionId}
    public ResponseEntity<MissionGetDto> getById(@PathVariable int missionId) {
        MissionGetDto mission = missionMapper.toMissionDto(missionService.findById(missionId)) ;
        return ResponseEntity.ok(mission);
    }

    @Operation(summary = "Create a mission object")
    @PostMapping // POST: localhost:8080/api/v1/game/{gameId}/mission/
    public ResponseEntity<?> add(@PathVariable int gameId, @RequestBody MissionPostDto mission) {
        Mission newMission = missionService.add(missionMapper.toMission(mission, gameId));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newMission.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update a mission object. Admin only")
    @PutMapping("{missionId}") // PUT: localhost:8080/api/v1/game/{gameId}/mission/{missionId}
    public ResponseEntity<?> update(@PathVariable int gameId, @PathVariable int missionId, @RequestBody MissionPostDto mission) {
        missionService.update(missionMapper.toMission(mission, gameId, missionId));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a mission. Admin only")
    @DeleteMapping("{missionId}") // DELETE: localhost:8080/api/v1/game/{gameId}/mission/{missionId}
    public ResponseEntity<?> delete(@PathVariable int missionId) {
        missionService.deleteById(missionId);
        return ResponseEntity.noContent().build();
    }
}
