package noroff.project.hvz.models.dtos;

import java.time.LocalDateTime;

public class MissionGetDto {

    private int id;
    private String name;
    private Boolean isHumanVisible;
    private Boolean isZombieVisible;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double latitude;
    private Double longitude;
}
