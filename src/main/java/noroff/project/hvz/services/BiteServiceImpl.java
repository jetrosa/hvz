package noroff.project.hvz.services;

import jakarta.transaction.Transactional;
import noroff.project.hvz.customexceptions.DuplicateKeyException;
import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.Bite;
import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.repositories.BiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class BiteServiceImpl implements BiteService {
    private final BiteRepository biteRepository;
    private final Logger logger = LoggerFactory.getLogger(BiteServiceImpl.class);
    private final SquadMemberService squadMemberService;
    private final PlayerService playerService;
    private final ChatMessageService chatMessageService;

    public BiteServiceImpl(BiteRepository biteRepository, SquadMemberService squadMemberService, PlayerService playerService, ChatMessageService chatMessageService) {
        this.biteRepository = biteRepository;
        this.squadMemberService = squadMemberService;
        this.playerService = playerService;
        this.chatMessageService = chatMessageService;
    }

    @Override
    public Bite findById(Integer id) {
        return biteRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No kill exists with ID: " + id);
                            return new RecordNotFoundException("Kill", id);
                        }
                );
    }

    @Override
    public Collection<Bite> findAll() {
        return biteRepository.findAll();
    }

    /**
     * Add a bite to the database. Also sends a message to the faction chat and player's squad chat
     * informing that the player has turned into a zombie. Sets the player status to zombie.
     * <p><p><p>
     * Throws an exception if the player has been bitten already.
     *
     * @param bite bite object
     * @return created bite object
     */
    @Transactional
    @Override
    public Bite add(Bite bite) {
        boolean isVictimAlreadyBitten = biteRepository.existsByVictimId(bite.getVictim().getId());
        if (!isVictimAlreadyBitten) {
            Player player = bite.getVictim();
            SquadMember s = squadMemberService.findByPlayerId(player.getId());
            if (s != null) {
                ChatMessage bittenSquadChat = new ChatMessage();
                bittenSquadChat.setGame(player.getGame());
                bittenSquadChat.setPlayer(player);
                bittenSquadChat.setMessage("**turned into a zombie**");
                chatMessageService.addSquadChat(bittenSquadChat, s.getSquad().getId());
                ChatMessage bittenFactionChat = new ChatMessage();
                bittenFactionChat.setGame(player.getGame());
                bittenFactionChat.setPlayer(player);
                bittenFactionChat.setMessage("**turned into a zombie**");
                bittenFactionChat.setIsHumanGlobal(true);
                chatMessageService.add(bittenFactionChat);

                squadMemberService.delete(s);
            }
            bite.getVictim().setIsHuman(false);
            playerService.update(player);
            return biteRepository.save(bite);
        } else
            throw new DuplicateKeyException("victim");
    }

    @Override
    public Bite update(Bite entity) {
        return biteRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(Bite bite) {
        biteRepository.delete(bite);
    }

    @Override
    public Set<Bite> findAllByGameId(int gameId) {
        return biteRepository.findAllByGameId(gameId);
    }
}
