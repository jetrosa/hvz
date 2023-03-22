package noroff.project.hvz.models.dtos;

import lombok.Data;
import noroff.project.hvz.models.GameState;
import noroff.project.hvz.models.MapCoordinate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GameGetDto {
    private int id;
    private String name;
    private String description;
    private GameState gameState;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<MapCoordinate> mapCoordinates;
}
