package noroff.project.hvz.services;

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
    public Game findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<Game> findAll() {
        return null;
    }

    @Override
    public Game add(Game entity) {
        return null;
    }

    @Override
    public Game update(Game entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Game entity) {

    }
}
