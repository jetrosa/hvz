package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.RecordNotFoundException;
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
    public Player findById(Integer id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No player exists with ID: " + id);
                            return new RecordNotFoundException("Player", id);
                        }
                );
    }

    @Override
    public Collection<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player add(Player entity) {
        return playerRepository.save(entity);
    }

    @Override
    public Player update(Player entity) {
        return playerRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(Player player) {
        playerRepository.delete(player);
        //todo chatmessage
    }
}
