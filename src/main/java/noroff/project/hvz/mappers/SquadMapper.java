package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Squad;
import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadDto;
import noroff.project.hvz.models.dtos.SquadGetDto;
import noroff.project.hvz.models.dtos.SquadPostDto;
import noroff.project.hvz.services.GameService;
import noroff.project.hvz.services.PlayerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SquadMapper {
    @Autowired
    protected GameService gameService;
    @Autowired
    protected PlayerService playerService;
    public abstract Set<SquadGetDto> toSquadDto(Set<Squad> squads);
    @Mapping(target = "squadMembers", source = "squadMembers", qualifiedByName = "squadMemberInfo")
    public abstract SquadGetDto toSquadDto(Squad squad);
    @Mapping(target = "game", source = "gameId", qualifiedByName = "gameIdToGame")
    public abstract Squad toSquad(SquadPostDto squadPostDto, int gameId);

    @Named("gameIdToGame")
    Game mapGameIdToGame(int id) {
        return gameService.findById(id);
    }

    @Named("squadMemberInfo")
    List<PlayerWithNameAndSquadDto> mapSquadMembers(List<SquadMember> squadMembers) {
        return squadMembers.stream()
                .map(i -> playerService.findPlayerWithNameAndSquadById(i.getPlayer().getId()))
                .collect(Collectors.toList());
    }
}
