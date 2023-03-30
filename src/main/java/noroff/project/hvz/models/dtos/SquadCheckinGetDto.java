package noroff.project.hvz.models.dtos;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class SquadCheckinGetDto {
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private Double latitude;
    private Double longitude;
}
