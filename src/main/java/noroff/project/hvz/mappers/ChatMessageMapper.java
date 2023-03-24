package noroff.project.hvz.mappers;

import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.ChatMessageGetDto;
import noroff.project.hvz.models.dtos.ChatMessagePostDto;
import noroff.project.hvz.models.dtos.ChatMessageSquadPostDto;
import noroff.project.hvz.services.GameService;
import noroff.project.hvz.services.PlayerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class ChatMessageMapper {
    @Autowired
    protected GameService gameService;
    @Autowired
    protected PlayerService playerService;
    public abstract ChatMessageGetDto toChatMessageDto(ChatMessage chatMessage);
    public abstract List<ChatMessageGetDto> toChatMessageDto(Collection<ChatMessage> chatMessages);

    @Mapping(target = "game", source = "gameId", qualifiedByName = "gameIdToGame")
    @Mapping(target = "player", source = "playerId", qualifiedByName = "playerIdToPlayer")
    public abstract ChatMessage toChatMessage(ChatMessagePostDto chatMessagePostDto,  int gameId, int playerId);

    @Mapping(target = "game", source = "gameId", qualifiedByName = "gameIdToGame")
    public abstract ChatMessage toChatMessage(ChatMessagePostDto chatMessagePostDto,  int gameId, Player player);

    @Mapping(target = "game", source = "gameId", qualifiedByName = "gameIdToGame")
    public abstract ChatMessage toSquadChatMessage(ChatMessageSquadPostDto chatMessagePostDto, int gameId, Player player);

    @Named("gameIdToGame")
    Game mapGameIdToGame(int gameId) {
        return gameService.findById(gameId);
    }

    @Named("playerIdToPlayer")
    Player mapPlayerIdToPlayer(int playerId) {
        return playerService.findById(playerId);
    }
}
