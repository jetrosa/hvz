package noroff.project.hvz.repositories;

import noroff.project.hvz.models.Bite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BiteRepository extends JpaRepository<Bite, Integer> {
    Set<Bite> findAllByGameId(final int gameId);
    boolean existsByVictimId(int victimId);
}
