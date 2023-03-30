package noroff.project.hvz.services;

import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.PlayerUpdateDto;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadDto;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadWithoutBiteCodeDto;

import java.util.List;

public interface PlayerService extends CrudService<Player, Integer> {
    Player findByGameIdAndAppUserUuid(int gameId, String uuid);
    Player addWithDefaultValues(String userUuid, int gameId);
    List<Player> findAllByGameId(Integer gameId);
    PlayerWithNameAndSquadDto findPlayerWithNameAndSquadById(int playerId);
    PlayerWithNameAndSquadWithoutBiteCodeDto findPlayerWithNameAndSquadByIdWithoutBiteCode(Player player);
    Player findPlayerByBiteCode(String biteCode);
    void updateWithDto(PlayerUpdateDto player, int playerId);
    List<Player> findAllByAppUserUuid(String uuid);
}
