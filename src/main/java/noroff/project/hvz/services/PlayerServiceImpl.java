package noroff.project.hvz.services;

import noroff.project.hvz.models.Player;
import noroff.project.hvz.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepository playerRepository;
    private final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository=playerRepository;
    }
    @Override
    public Player findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<Player> findAll() {
        return null;
    }

    @Override
    public Player add(Player entity) {
        return null;
    }

    @Override
    public Player update(Player entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Player entity) {

    }
}
