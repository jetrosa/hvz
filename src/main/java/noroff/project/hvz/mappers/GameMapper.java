package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.GameGetDto;
import noroff.project.hvz.models.dtos.GamePostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class GameMapper {
    @Mapping(target = "playerCount", source = "players", qualifiedByName = "playersToPlayerCount")
    public abstract GameGetDto toGameDto(Game game);

    public abstract List<GameGetDto> toGameDto(Collection<Game> games);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gameState", ignore = true)
    @Mapping(target = "mapCoordinates", ignore = true)
    @Mapping(target = "chatMessages", ignore = true)
    @Mapping(target = "bites", ignore = true)
    @Mapping(target = "players", ignore = true)
    @Mapping(target = "squads", ignore = true)
    @Mapping(target = "squadCheckins", ignore = true)
    @Mapping(target = "missions", ignore = true)
    public abstract Game toGame(GamePostDto gamePostDto);

    @Named("playersToPlayerCount")
    int mapPlayersToPlayerCount(List<Player> players) {
        return players.size();
    }
}
