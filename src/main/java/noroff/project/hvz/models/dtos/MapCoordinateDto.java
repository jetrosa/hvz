package noroff.project.hvz.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MapCoordinateDto {
    private Double latitude;
    private Double longitude;
}
