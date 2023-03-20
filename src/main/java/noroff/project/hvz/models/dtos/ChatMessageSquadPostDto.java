package noroff.project.hvz.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChatMessageSquadPostDto {
    @NotBlank(message = "Message may not be blank")
    @Size(min = 1, max = 300, message = "Message must be between 1 and 300 characters long")
    private String message;
}
