package noroff.project.hvz.repositories;

import noroff.project.hvz.models.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {
    Set<Mission> findAllByGameId(final int gameId);
}
