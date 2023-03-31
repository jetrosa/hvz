package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.PlayerGetDto;
import noroff.project.hvz.services.PlayerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PlayerMapper {
    @Autowired
    protected PlayerService playerService;

    @Mapping(target = "gameId", source = "game.id")
    @Mapping(target = "playerId", source = "id")
    public abstract PlayerGetDto toPlayerGetDto(Player player);

    public abstract List<PlayerGetDto> toPlayerGetDto(List<Player> player);
}
