package noroff.project.hvz.models.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class SquadCheckinPostDto {
    @NotNull(message = "Latitude may not be null")
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    private Double latitude;
    @NotNull(message = "Longitude may not be null")
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    private Double longitude;
}
