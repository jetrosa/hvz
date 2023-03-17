package noroff.project.hvz.models.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class SquadGetDto {
    private String name;
    private Boolean isHuman;
    private Set<SquadMemberWithPlayerNameDto> squadMembers;
}
