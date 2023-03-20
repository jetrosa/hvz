package noroff.project.hvz.models.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageGetDto {
    private int id;
    private String message;
    private Boolean isHumanGlobal;
    private Boolean isZombieGlobal;
    private LocalDateTime chat_time;
    private PlayerWithNameAndSquadWithoutBiteCodeDto player;
}
