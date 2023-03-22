package noroff.project.hvz.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerWithNameAndSquadDto {

    private Boolean isHuman;
    private String biteCode;


    private String fullName;
    private Integer squadId;
}
