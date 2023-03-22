package noroff.project.hvz.repositories;

import noroff.project.hvz.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Set<Player> findAllByGameId(final int gameId);
    Player findPlayerByBiteCode(String biteCode);
}
