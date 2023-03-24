package noroff.project.hvz.repositories;

import noroff.project.hvz.models.AppUser;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @EntityGraph(attributePaths = {"appUser"})
    List<Player> findAllByGameId(final int gameId);
    List<Player> findAllByGameIdAndIsPatientZeroIsTrue(final int gameId);
    Player findPlayerByBiteCode(String biteCode);
    boolean existsByBiteCode(String biteCode);
    boolean existsByGameAndAppUser(Game game, AppUser user);
    @EntityGraph(attributePaths = {"appUser"})
    Optional<Player> findByGameIdAndAppUserUuid(int gameId, String uuid);
    @EntityGraph(attributePaths = {"appUser"})
    Optional<List<Player>> findAllByAppUserUuid(String uuid);
}
