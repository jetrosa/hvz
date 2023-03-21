package noroff.project.hvz.services;

import noroff.project.hvz.models.AppUser;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadDto;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadWithoutBiteCodeDto;

import java.util.Set;

public interface PlayerService extends CrudService<Player, Integer> {
    Player addWithDefaultValues(AppUser a, Game g);
    Set<Player> findAllByGameId(Integer gameId);
    PlayerWithNameAndSquadDto findPlayerWithNameAndSquadById(int playerId);
    PlayerWithNameAndSquadWithoutBiteCodeDto findPlayerWithNameAndSquadByIdWithoutBiteCode(Player player);
    Player findPlayerByBiteCode(String biteCode);
}
