package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.dtos.GameGetDto;
import noroff.project.hvz.models.dtos.GamePostDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class GameMapper {
    public abstract GameGetDto toGameDto(Game game);
    public abstract Game toGame(GamePostDto gamePostDto);
}
