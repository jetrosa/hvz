package noroff.project.hvz.models.dtos;

import lombok.Data;
import noroff.project.hvz.models.GameState;
import noroff.project.hvz.models.MapCoordinate;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class GameGetDto {
    private int id;
    private String name;
    private String description;
    private GameState gameState;
    private OffsetDateTime startDateTime;
    private OffsetDateTime endDateTime;
    private List<MapCoordinate> mapCoordinates;
    private int playerCount;
}
