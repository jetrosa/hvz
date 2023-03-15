package noroff.project.hvz.repositories;

import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.SquadMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SquadMemberRepository extends JpaRepository<SquadMember, Integer> {
    Set<SquadMember> findAllByPlayerId(final int playerId);
    Set<SquadMember> findAllBySquadId(final int squadId);
}
