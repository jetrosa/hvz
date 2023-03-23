package noroff.project.hvz.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SquadGetDto {
    private int id;
    private String name;
    private Boolean isHuman;
    private List<PlayerWithNameAndSquadDto> squadMembers;
}
