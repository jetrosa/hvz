package noroff.project.hvz.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerWithNameAndSquadWithoutBiteCodeDto {
    private Boolean isHuman;
    private String fullName;
    private Integer squadId;
}
