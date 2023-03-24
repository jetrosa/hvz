package noroff.project.hvz.services;

import jakarta.transaction.Transactional;
import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.GameState;
import noroff.project.hvz.models.MapCoordinate;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.MapCoordinateDto;
import noroff.project.hvz.repositories.GameRepository;
import noroff.project.hvz.repositories.MapCoordinateRepository;
import noroff.project.hvz.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final MapCoordinateRepository mapCoordinateRepository;

    private final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);
    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository, MapCoordinateRepository mapCoordinateRepository){
        this.gameRepository=gameRepository;
        this.mapCoordinateRepository=mapCoordinateRepository;
        this.playerRepository = playerRepository;
    }
    @Override
    public Game findById(Integer id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No game exists with ID: " + id);
                            return new RecordNotFoundException("Game", id);
                        }
                );
    }

    @Override
    public Collection<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game add(Game entity) {
        return gameRepository.save(entity);
    }

    @Override
    public Game update(Game entity) {
        return gameRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(Game game) {
        gameRepository.delete(game);
    }

    @Transactional
    @Override
    public void createGameWithMap(Game game, List<MapCoordinateDto> mapCoordinateDtos) {
        Game g=add(game);
        List<MapCoordinate> mapCoordinates = new ArrayList<>();
        for(MapCoordinateDto dto: mapCoordinateDtos){
            MapCoordinate m = new MapCoordinate();
            m.setLatitude(dto.getLatitude());
            m.setLongitude(dto.getLongitude());
            m.setGame(g);
            mapCoordinates.add(m);
        }
        mapCoordinateRepository.saveAll(mapCoordinates);
    }
    @Transactional
    @Override
    public void setGameInfection(Game game) {
       List<Player> players = playerRepository.findAllByGameId(game.getId());
       if(players.size()>0) {
           Random rnd = new Random();
           int infectedPlayerNum = rnd.nextInt(players.size());
           players.get(infectedPlayerNum).setIsPatientZero(true);
       }
       game.setGameState(GameState.INFECTION);
    }

    @Transactional
    @Override
    public void setGameStart(Game game) {
        List<Player> players = playerRepository.findAllByGameIdAndIsPatientZeroIsTrue(game.getId());
        for(Player p: players){
            p.setIsHuman(false);
        }
        game.setGameState(GameState.IN_PROGRESS);
    }

    @Override
    public void setGameComplete(Game game) {
        game.setGameState(GameState.COMPLETE);
    }
}
