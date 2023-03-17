package noroff.project.hvz.models.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageGetDto {
    private int id;
    private String message;
    private boolean isHumanGlobal;
    private boolean isZombieGlobal;
    private LocalDateTime chat_time;
}
