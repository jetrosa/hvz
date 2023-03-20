package noroff.project.hvz.services;

import jakarta.transaction.Transactional;
import noroff.project.hvz.models.Squad;
import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.models.dtos.SquadMemberWithPlayerNameDto;

public interface SquadMemberService extends CrudService<SquadMember, Integer> {
    SquadMemberWithPlayerNameDto getSquadMemberWithPlayerName(SquadMember squadMember);
    void leaveSquad(int playerId);
    void joinSquad(int squadId, int playerId);

    @Transactional
    void createAndJoin(Squad squad, int playerId);
}
