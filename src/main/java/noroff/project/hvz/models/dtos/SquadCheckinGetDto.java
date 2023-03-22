package noroff.project.hvz.models.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SquadCheckinGetDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double latitude;
    private Double longitude;
}
