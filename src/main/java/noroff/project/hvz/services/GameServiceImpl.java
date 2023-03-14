package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.repositories.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);
    public GameServiceImpl(GameRepository gameRepository){
        this.gameRepository=gameRepository;
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
}
