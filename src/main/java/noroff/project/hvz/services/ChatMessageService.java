package noroff.project.hvz.services;

import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.models.Player;

import java.util.Set;

public interface ChatMessageService extends CrudService<ChatMessage, Integer>{
    Set<ChatMessage> findAllByGameId(final int gameId);
    Set<ChatMessage> findAllBySquadId(final int squadId);
    Set<ChatMessage> findAllGlobalAndPlayerFactionMessages(final int gameId, final Player player);
}
