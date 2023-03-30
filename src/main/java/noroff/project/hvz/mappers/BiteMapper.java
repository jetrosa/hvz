package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Bite;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.BiteGetDto;
import noroff.project.hvz.models.dtos.BitePostDto;
import noroff.project.hvz.models.dtos.BiteUpdateDto;
import noroff.project.hvz.services.GameService;
import noroff.project.hvz.services.PlayerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BiteMapper {
    @Autowired
    protected GameService gameService;
    @Autowired
    protected PlayerService playerService;

    @Mapping(target = "victimId", source = "victim.id")
    @Mapping(target = "biterId", source = "biter.id")
    public abstract BiteGetDto toBiteDto(Bite bite);

    @Mapping(target = "victimId", source = "victim.id")
    @Mapping(target = "biterId", source = "biter.id")
    public abstract List<BiteGetDto> toBiteDto(Collection<Bite> bite);

    @Mapping(target = "game", source = "gameId", qualifiedByName = "gameIdToGame")
    @Mapping(target = "victim", source = "bitePostDto.biteCode", qualifiedByName = "biteCodeToPlayer")
    @Mapping(target = "id", ignore = true)
    public abstract Bite toBitePost(BitePostDto bitePostDto, int gameId, Player biter);

    @Mapping(target = "game", source = "gameId", qualifiedByName = "gameIdToGame")
    @Mapping(target = "victim", source = "biteUpdateDto.victimId", qualifiedByName = "playerIdToPlayer")
    @Mapping(target = "biter", source = "biteUpdateDto.biterId", qualifiedByName = "playerIdToPlayer")
    @Mapping(target = "id", ignore = true)
    public abstract Bite toBiteUpdate(BiteUpdateDto biteUpdateDto, int gameId, int biteId);

    @Named("playerIdToPlayer")
    Player mapPlayerIdToPlayer(int playerId) {
        return playerService.findById(playerId);
    }

    @Named("biteCodeToPlayer")
    Player mapBiteCodeToPlayer(String biteCode) {
        return playerService.findPlayerByBiteCode(biteCode);
    }

    @Named("gameIdToGame")
    Game mapGameIdToGame(int gameId) {
        return gameService.findById(gameId);
    }
}
