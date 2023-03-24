package noroff.project.hvz.models.dtos;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ChatMessageGetDto {
    private String message;
    private Boolean isHumanGlobal;
    private Boolean isZombieGlobal;
    private OffsetDateTime chat_time;
    private PlayerWithNameAndSquadWithoutBiteCodeDto player;
}
