package noroff.project.hvz.models.dtos;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class MissionGetDto {

    private int id;
    private String name;
    private Boolean isHumanVisible;
    private Boolean isZombieVisible;
    private String description;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private Double latitude;
    private Double longitude;
}
