package noroff.project.hvz.services;

import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.ChatMessageGetDto;

import java.util.List;
import java.util.Set;

public interface ChatMessageService extends CrudService<ChatMessage, Integer>{
    ChatMessage addSquadChat(ChatMessage message, int squadId);
    Set<ChatMessage> findAllByGameId(final int gameId);
    List<ChatMessageGetDto> findAllBySquadId(final int squadId);
    List<ChatMessageGetDto> findAllGlobalAndPlayerFactionMessages(final int gameId, final Player player);
    ChatMessage addGlobalOrFactionChat(ChatMessage message);
}
