package noroff.project.hvz.repositories;

import noroff.project.hvz.models.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Integer> {
    Set<Squad> findAllByGameId(final int gameId);
}
