package noroff.project.hvz.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GameAndCoordinatesDto {
    private GamePostDto gameDto;
    private List<MapCoordinateDto> mapCoordinateDtos;
}
