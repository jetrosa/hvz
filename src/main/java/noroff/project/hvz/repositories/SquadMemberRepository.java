package noroff.project.hvz.repositories;

import noroff.project.hvz.models.SquadMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadMemberRepository extends JpaRepository<SquadMember, Integer> {
}
