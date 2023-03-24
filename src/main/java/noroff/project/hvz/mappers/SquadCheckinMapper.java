package noroff.project.hvz.mappers;

import noroff.project.hvz.models.*;
import noroff.project.hvz.models.dtos.SquadCheckinGetDto;
import noroff.project.hvz.models.dtos.SquadCheckinPostDto;
import noroff.project.hvz.services.GameService;
import noroff.project.hvz.services.SquadMemberService;
import noroff.project.hvz.services.SquadService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SquadCheckinMapper {
    @Autowired
    protected GameService gameService;
    @Autowired
    protected SquadService squadService;
    @Autowired
    protected SquadMemberService squadMemberService;
    public abstract List<SquadCheckinGetDto> toSquadCheckinDto(List<SquadCheckin> squadCheckin);
    @Mapping(target = "game", source = "gameId", qualifiedByName = "gameIdToGame")
    @Mapping(target = "squad", source = "squadId", qualifiedByName = "squadIdToSquad")
    @Mapping(target = "squadMember", source = "player")
    @Mapping(target = "id", ignore = true)
    public abstract SquadCheckin toSquadCheckin(SquadCheckinPostDto squadCheckinPostDto, int gameId, int squadId, Player player);

    @Named("gameIdToGame")
    Game mapGameIdToGame(int gameId) {
        return gameService.findById(gameId);
    }
    @Named("squadIdToSquad")
    Squad mapSquadIdToSquad(int squadId) {
        return squadService.findById(squadId);
    }
    @Named("playerIdToSquadMember")
    SquadMember mapPlayerIdToSquadMember(int playerId) {
        return squadMemberService.findByPlayerId(playerId);
    }
}
