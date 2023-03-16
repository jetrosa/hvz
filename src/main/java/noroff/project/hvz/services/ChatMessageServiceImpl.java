package noroff.project.hvz.services;

import jakarta.transaction.Transactional;
import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.repositories.ChatMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{
    private final ChatMessageRepository chatMessageRepository;
    private final Logger logger = LoggerFactory.getLogger(ChatMessageServiceImpl.class);
    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository){
        this.chatMessageRepository = chatMessageRepository;
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
    public Set<ChatMessage> findAllBySquadId(int squadId) {
        return chatMessageRepository.findAllBySquadId(squadId);
    }

    @Transactional
    @Override
    public Set<ChatMessage> findAllByGameIdAndIsHumanGlobalAndIsZombieGlobal(int gameId, boolean isHumanGlobal, boolean isZombieGlobal) {
        return chatMessageRepository.findAllByGameIdAndIsHumanGlobalAndIsZombieGlobal(gameId, isHumanGlobal,isZombieGlobal);
    }
}
