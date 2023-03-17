package noroff.project.hvz.services;

import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.models.dtos.SquadMemberWithPlayerNameDto;

public interface SquadMemberService extends CrudService<SquadMember, Integer> {
    SquadMemberWithPlayerNameDto getSquadMemberWithPlayerName(SquadMember squadMember);
}
