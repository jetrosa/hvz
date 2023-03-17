package noroff.project.hvz.repositories;

import noroff.project.hvz.models.MapCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapCoordinateRepository extends JpaRepository<MapCoordinate, Integer> {
}
