package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Mission;
import noroff.project.hvz.models.dtos.MissionGetDto;
import noroff.project.hvz.models.dtos.MissionPostDto;
import noroff.project.hvz.services.GameService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MissionMapper {
    @Autowired
    protected GameService gameService;
    public abstract MissionGetDto toMissionDto(Mission mission);
    @Mapping(target = "game", source = "gameId", qualifiedByName = "gameIdToGame")
    public abstract Mission toMission(MissionPostDto missionPostDto, int gameId);

    @Named("gameIdToGame")
    Game mapGameIdToGame(int id) {
        return gameService.findById(id);
    }
}
