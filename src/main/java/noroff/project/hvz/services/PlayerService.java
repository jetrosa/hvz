package noroff.project.hvz.services;

import noroff.project.hvz.models.Player;

import java.util.Set;

public interface PlayerService extends CrudService<Player, Integer> {
    Set<Player> findAllByGameId(Integer gameId);
}
