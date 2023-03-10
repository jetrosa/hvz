package noroff.project.hvz.repositories;

import noroff.project.hvz.models.Kill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KillRepository extends JpaRepository<Kill, Integer> {
}
