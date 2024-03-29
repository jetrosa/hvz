package noroff.project.hvz.models.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerAdminPostDto {
    @NotNull(message = "User id may not be null")
    private Integer userId;
}
