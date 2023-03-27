package noroff.project.hvz.services;

import jakarta.transaction.Transactional;
import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.Squad;
import noroff.project.hvz.models.dtos.ChatMessageGetDto;
import noroff.project.hvz.repositories.ChatMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{
    private final ChatMessageRepository chatMessageRepository;
    private final Logger logger = LoggerFactory.getLogger(ChatMessageServiceImpl.class);
    private final PlayerService playerService;
    private final SquadService squadService;

    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository, PlayerService playerService, SquadService squadService){
        this.chatMessageRepository = chatMessageRepository;
        this.playerService = playerService;
        this.squadService = squadService;
    }
    @Override
    public ChatMessage findById(Integer id) {
        return chatMessageRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No chat message exists with ID: " + id);
                            return new RecordNotFoundException("Chat message", id);
                        }
                );
    }

    @Override
    public Collection<ChatMessage> findAll() {
        return chatMessageRepository.findAll();
    }

    @Override
    public ChatMessage add(ChatMessage entity) {
        return chatMessageRepository.save(entity);
    }

    @Override
    public ChatMessage addSquadChat(ChatMessage message, int squadId) {
        Squad s = squadService.findById(squadId);
        message.setSquad(s);
        return add(message);
    }

    @Override
    public ChatMessage update(ChatMessage entity) {
        return chatMessageRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(ChatMessage chatMessage) {
        chatMessageRepository.delete(chatMessage);
    }

    @Override
    public Set<ChatMessage> findAllByGameId(int gameId) {
        return chatMessageRepository.findAllByGameId(gameId);
    }

    @Override
    public List<ChatMessageGetDto> findAllBySquadId(int squadId) {
        Set<ChatMessage> messages = chatMessageRepository.findAllBySquadId(squadId);
        return chatMessagesToDtos(messages);
    }


    @Transactional
    @Override
    public List<ChatMessageGetDto> findAllGlobalAndPlayerFactionMessages(int gameId, Player player) {
        boolean isHumanGlobal = player.getIsHuman();
        Set<ChatMessage> messages = chatMessageRepository.findAllByGameIdAndIsHumanGlobalAndSquadIsNullOrIsZombieGlobalAndSquadIsNull(gameId, isHumanGlobal, !isHumanGlobal);
        return chatMessagesToDtos(messages);
    }

    private List<ChatMessageGetDto> chatMessagesToDtos(Set<ChatMessage> messages){
        List<ChatMessageGetDto> messageDtos = new ArrayList<>();
        for(ChatMessage m: messages){
            ChatMessageGetDto c = new ChatMessageGetDto();
            c.setMessage(m.getMessage());
            c.setChat_time(m.getChat_time());
            c.setIsHumanGlobal(m.getIsHumanGlobal());
            c.setIsZombieGlobal(m.getIsZombieGlobal());
            c.setPlayer(playerService.findPlayerWithNameAndSquadByIdWithoutBiteCode(m.getPlayer()));
            messageDtos.add(c);
        }
        return messageDtos;
    }
}
