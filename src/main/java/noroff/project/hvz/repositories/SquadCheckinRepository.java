package noroff.project.hvz.repositories;

import noroff.project.hvz.models.SquadCheckin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadCheckinRepository extends JpaRepository<SquadCheckin, Integer> {
}
