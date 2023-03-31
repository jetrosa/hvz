package noroff.project.hvz.services;

import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.dtos.MapCoordinateDto;

import java.util.List;

public interface GameService extends CrudService<Game, Integer> {
    Game createGameWithMap(Game game, List<MapCoordinateDto> mapCoordinateDtos);

    /**
     * Turn one of the players in the game to patient zero that will be turned into a zombie after a while.
     * <p>
     * Set the game state to INFECTION.
     *
     * @param game target game
     */
    void setGameInfection(Game game);

    /**
     * Find the patient zero players and turn them into zombies.
     * <p>
     * Set the game state to IN_PROGRESS.
     *
     * @param game target game
     */
    void setGameStart(Game game);

    /**
     * Set the game state to COMPLETE.
     *
     * @param game target game
     */
    void setGameComplete(Game game);
}
