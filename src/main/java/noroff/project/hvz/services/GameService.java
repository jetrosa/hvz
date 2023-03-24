package noroff.project.hvz.services;

import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.dtos.MapCoordinateDto;

import java.util.List;

public interface GameService extends CrudService<Game, Integer> {
    void createGameWithMap(Game game, List<MapCoordinateDto> mapCoordinateDtos);

    void setGameInfection(Game game);

    void setGameStart(Game game);

    void setGameComplete(Game game);
}
