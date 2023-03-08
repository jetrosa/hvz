package noroff.project.hvz.repositories;

import noroff.project.hvz.models.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Integer> {
}
