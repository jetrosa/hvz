package noroff.project.hvz.models.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerUpdateDto {
    @NotNull(message = "Player type may not be null (false: zombie)")
    private Boolean isHuman;
}
