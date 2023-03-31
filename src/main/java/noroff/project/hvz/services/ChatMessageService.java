package noroff.project.hvz.services;

import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.dtos.ChatMessageGetDto;

import java.util.List;
import java.util.Set;

public interface ChatMessageService extends CrudService<ChatMessage, Integer> {
    ChatMessage addSquadChat(ChatMessage message, int squadId);

    Set<ChatMessage> findAllByGameId(final int gameId);

    List<ChatMessageGetDto> findAllBySquadId(final int squadId);

    List<ChatMessageGetDto> findAllGlobalAndPlayerFactionMessages(final int gameId, final Player player);

    /**
     * Add the message to global of faction chat based on the humanGlobal and zombieGlobal params in the message.
     * If both are true, it is a global message. If just one is true, the chat belongs to that faction.
     * <p><p><p>
     * Throws an error if both global params are false.
     *
     * @param message message body
     * @return created chat message
     */
    ChatMessage addGlobalOrFactionChat(ChatMessage message);
}
