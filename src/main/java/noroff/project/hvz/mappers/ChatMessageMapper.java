package noroff.project.hvz.mappers;

import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.ChatMessagePostDto;
import noroff.project.hvz.models.dtos.ChatMessageSquadPostDto;
import noroff.project.hvz.services.GameService;
import noroff.project.hvz.services.PlayerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class ChatMessageMapper {
    @Autowired
    protected GameService gameService;
    @Autowired
    protected PlayerService playerService;

    @Mapping(target = "game", source = "gameId", qualifiedByName = "gameIdToGame")
    @Mapping(target = "player", source = "player")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "chat_time", ignore = true)
    @Mapping(target = "squad", ignore = true)
    public abstract ChatMessage toChatMessage(ChatMessagePostDto chatMessagePostDto,  int gameId, Player player);

    @Mapping(target = "game", source = "gameId", qualifiedByName = "gameIdToGame")
    @Mapping(target = "player", source = "player")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "chat_time", ignore = true)
    @Mapping(target = "squad", ignore = true)
    @Mapping(target = "isHumanGlobal", constant="false")
    @Mapping(target = "isZombieGlobal", constant="false")
    public abstract ChatMessage toSquadChatMessage(ChatMessageSquadPostDto chatMessagePostDto, int gameId, Player player);

    @Named("gameIdToGame")
    Game mapGameIdToGame(int gameId) {
        return gameService.findById(gameId);
    }
}
