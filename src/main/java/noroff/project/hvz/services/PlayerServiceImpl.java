package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.DuplicateKeyException;
import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.customexceptions.UserNotFoundException;
import noroff.project.hvz.models.AppUser;
import noroff.project.hvz.models.Game;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.models.dtos.PlayerUpdateDto;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadDto;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadWithoutBiteCodeDto;
import noroff.project.hvz.repositories.PlayerRepository;
import noroff.project.hvz.repositories.SquadMemberRepository;
import noroff.project.hvz.utils.RandomIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
    private final SquadMemberRepository squadMemberRepository;
    private final GameService gameService;
    private final AppUserService appUserService;

    public PlayerServiceImpl(PlayerRepository playerRepository, SquadMemberRepository squadMemberRepository, GameService gameService, AppUserService appUserService) {
        this.playerRepository = playerRepository;
        this.squadMemberRepository = squadMemberRepository;
        this.gameService = gameService;
        this.appUserService = appUserService;
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
    public Player findByGameIdAndAppUserUuid(int gameId, String uuid) {
        return playerRepository.findByGameIdAndAppUserUuid(gameId, uuid)
                .orElseThrow(() -> {
                            logger.warn("No player exists with UUID: " + uuid);
                            return new UserNotFoundException();
                        }
                );
    }

    @Override
    public List<Player> findAllByAppUserUuid(String uuid) {
        return playerRepository.findAllByAppUserUuid(uuid).orElseThrow(() -> {
                    logger.warn("No player exists with UUID: " + uuid);
                    return new UserNotFoundException();
                }
        );
    }

    @Override
    public Player addWithDefaultValues(String userUuid, int gameId) {
        Game game = gameService.findById(gameId);
        if (game == null)
            throw new RecordNotFoundException("game", gameId);
        AppUser user = appUserService.findByUuid(userUuid);

        if (playerRepository.existsByGameAndAppUser(game, user))
            throw new DuplicateKeyException("game, user");

        String biteCode = generateBiteCode();
        if (biteCode == null)
            throw new DuplicateKeyException("generated bitecode");

        Player player = new Player();
        player.setAppUser(user);
        player.setGame(game);
        player.setIsHuman(true);
        player.setBiteCode(generateBiteCode());

        return playerRepository.save(player);
    }

    @Override
    public Player update(Player entity) {
        return playerRepository.save(entity);
    }

    @Override
    public void updateWithDto(PlayerUpdateDto dto, int playerId) {
        Player player = findById(playerId);
        player.setIsHuman(dto.getIsHuman());
        update(player);
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
    public List<Player> findAllByGameId(Integer gameId) {
        return playerRepository.findAllByGameId(gameId);
    }

    @Override
    public PlayerWithNameAndSquadDto findPlayerWithNameAndSquadById(int playerId) {
        Player p = findById(playerId);
        AppUser a = p.getAppUser();
        String fullName = a.getFirstName() + " " + a.getLastName();
        SquadMember s = squadMemberRepository.findByPlayerId(p.getId());
        Integer squadId = null;
        if (s != null) squadId = s.getSquad().getId();

        return new PlayerWithNameAndSquadDto(p.getIsHuman(), p.getBiteCode(), fullName, squadId);
    }

    @Override
    public PlayerWithNameAndSquadWithoutBiteCodeDto findPlayerWithNameAndSquadByIdWithoutBiteCode(Player p) {
        AppUser a = p.getAppUser();
        String fullName = a.getFirstName() + " " + a.getLastName();
        SquadMember s = squadMemberRepository.findByPlayerId(p.getId());
        Integer squadId = null;
        if (s != null) squadId = s.getSquad().getId();

        return new PlayerWithNameAndSquadWithoutBiteCodeDto(p.getIsHuman(), fullName, squadId);
    }

    @Override
    public Player findPlayerByBiteCode(String biteCode) {
        Player player = playerRepository.findPlayerByBiteCode(biteCode);
        if (player != null)
            return player;
        else
            throw new RecordNotFoundException("player", biteCode);

    }

    private String generateBiteCode() {

        for (int i = 0; i < 10; i++) {
            String biteCode = RandomIdGenerator.GetBase36(4);
            if (!playerRepository.existsByBiteCode(biteCode)) {
                return biteCode;
            }

        }
        return null;
    }
}
