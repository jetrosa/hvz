package noroff.project.hvz.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SquadPostDto {
    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters long")
    private String name;
    @NotNull(message = "Squad type may not be null (false: zombie)")
    private Boolean isHuman;
}
