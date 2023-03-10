package noroff.project.hvz.services;

import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.repositories.ChatMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{
    private final ChatMessageRepository chatMessageRepository;
    private final Logger logger = LoggerFactory.getLogger(ChatMessageServiceImpl.class);
    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository){
        this.chatMessageRepository = chatMessageRepository;
    }
    @Override
    public ChatMessage findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<ChatMessage> findAll() {
        return null;
    }

    @Override
    public ChatMessage add(ChatMessage entity) {
        return null;
    }

    @Override
    public ChatMessage update(ChatMessage entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(ChatMessage entity) {

    }
}
