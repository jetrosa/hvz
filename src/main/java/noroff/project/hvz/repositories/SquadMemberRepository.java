package noroff.project.hvz.repositories;

import noroff.project.hvz.models.SquadMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SquadMemberRepository extends JpaRepository<SquadMember, Integer> {
    SquadMember findByPlayerId(final int playerId);
    Set<SquadMember> findAllBySquadId(final int squadId);
    boolean existsByPlayerId(final int playerId);
}
