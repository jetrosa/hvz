package noroff.project.hvz.repositories;

import noroff.project.hvz.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    boolean existsByUuid(final String uuid);
}
