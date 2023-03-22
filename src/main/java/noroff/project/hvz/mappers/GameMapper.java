package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.dtos.GameGetDto;
import noroff.project.hvz.models.dtos.GamePostDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class GameMapper {
    public abstract GameGetDto toGameDto(Game game);
    public abstract List<GameGetDto> toGameDto(Collection<Game> games);
    public abstract Game toGame(GamePostDto gamePostDto);
}
