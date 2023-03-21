package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.AppUser;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadDto;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadWithoutBiteCodeDto;
import noroff.project.hvz.repositories.PlayerRepository;
import noroff.project.hvz.repositories.SquadMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepository playerRepository;
    private final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
    private final SquadMemberRepository squadMemberRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, SquadMemberRepository squadMemberRepository){
        this.playerRepository=playerRepository;
        this.squadMemberRepository = squadMemberRepository;
    }
    @Override
    public Player findById(Integer id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No player exists with ID: " + id);
                            return new RecordNotFoundException("Player", id);
                        }
                );
    }

    @Override
    public Collection<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player add(Player entity) {
        return playerRepository.save(entity);
    }

    @Override
    public Player addWithDefaultValues(AppUser a, Game g) {
        Player player = new Player();
        player.setAppUser(a);
        player.setGame(g);
        player.setIsHuman(true);
        player.setBiteCode(generateBiteCode());

        return playerRepository.save(player);
    }

    @Override
    public Player update(Player entity) {
        return playerRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(Player player) {
        playerRepository.delete(player);
    }

    @Override
    public Set<Player> findAllByGameId(Integer gameId) {
        return playerRepository.findAllByGameId(gameId);
    }

    @Override
    public PlayerWithNameAndSquadDto findPlayerWithNameAndSquadById(int playerId) {
        Player  p = findById(playerId);
        AppUser a = p.getAppUser();
        String fullName = a.getFirstName()+" "+a.getLastName();
        SquadMember s = squadMemberRepository.findByPlayerId(p.getId());
        Integer squadId = null;
        if(s!=null) squadId = s.getSquad().getId();

        return new PlayerWithNameAndSquadDto(p.getIsHuman(),p.getBiteCode(),fullName,squadId);
    }

    @Override
    public PlayerWithNameAndSquadWithoutBiteCodeDto findPlayerWithNameAndSquadByIdWithoutBiteCode(Player p) {
        AppUser a = p.getAppUser();
        String fullName = a.getFirstName()+" "+a.getLastName();
        SquadMember s = squadMemberRepository.findByPlayerId(p.getId());
        Integer squadId = null;
        if(s!=null) squadId = s.getSquad().getId();

        return new PlayerWithNameAndSquadWithoutBiteCodeDto(p.getIsHuman(),fullName,squadId);
    }

    @Override
    public Player findPlayerByBiteCode(String biteCode) {
        Player player =  playerRepository.findPlayerByBiteCode(biteCode);
        if(player!=null)
            return player;
        else
            throw new RecordNotFoundException("player", biteCode);

    }

    private String generateBiteCode(){
        return "";
    }
}
