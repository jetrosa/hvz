package noroff.project.hvz.services;

import jakarta.transaction.Transactional;
import noroff.project.hvz.customexceptions.DuplicateKeyException;
import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.AppUser;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.Squad;
import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.models.dtos.SquadMemberWithPlayerNameDto;
import noroff.project.hvz.repositories.SquadMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class SquadMemberServiceImpl implements  SquadMemberService{
    private final SquadService squadService;
    private final SquadMemberRepository squadMemberRepository;
    private final Logger logger = LoggerFactory.getLogger(SquadMemberServiceImpl.class);
    private final PlayerService playerService;

    public SquadMemberServiceImpl(SquadMemberRepository squadMemberRepository, SquadService squadService, PlayerService playerService){
        this.squadMemberRepository=squadMemberRepository;
        this.squadService=squadService;
        this.playerService=playerService;
    }
    @Override
    public SquadMember findById(Integer id) {
        return squadMemberRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No squad member exists with ID: " + id);
                            return new RecordNotFoundException("Squad member", id);
                        }
                );
    }

    @Override
    public Collection<SquadMember> findAll() {
        return squadMemberRepository.findAll();
    }

    @Override
    public SquadMember add(SquadMember entity) {
        return squadMemberRepository.save(entity);
    }

    @Override
    public SquadMember update(SquadMember entity) {
        return squadMemberRepository.save(entity);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Transactional
    @Override
    public void delete(SquadMember squadMember) {
        int squadId = squadMember.getSquad().getId();
        squadMemberRepository.delete(squadMember);
        Set<SquadMember> remainingSquadMembers = squadMemberRepository.findAllBySquadId(squadId);
        if(remainingSquadMembers.isEmpty()){
            squadService.deleteById(squadId);
        }
    }

    @Override
    public SquadMemberWithPlayerNameDto getSquadMemberWithPlayerName(SquadMember squadMember) {
        SquadMemberWithPlayerNameDto dto = new SquadMemberWithPlayerNameDto();
        Player player = squadMember.getPlayer();
        AppUser user = player.getAppUser();
        String fullName = user.getFirstName()+" "+user.getLastName();

        dto.setRank(dto.getRank());
        dto.setFullName(fullName);
        return dto;
    }

    @Override
    public void joinSquad(int squadId, int playerId) {
        if(squadMemberRepository.existsByPlayerId(playerId))
            throw new DuplicateKeyException("player_id");

        Squad squad = squadService.findById(squadId);
        Player player = playerService.findById(playerId);
        SquadMember s = new SquadMember();
        s.setSquad(squad);
        s.setPlayer(player);
        add(s);
    }

    @Override
    public void leaveSquad(int playerId) {
        SquadMember s = squadMemberRepository.findByPlayerId(playerId);
        if(s==null){
            throw new RecordNotFoundException("squad member", playerId);
        }else
            delete(s);
    }

    @Transactional
    @Override
    public void createAndJoin(Squad squad, int playerId) {
        if(squadMemberRepository.existsByPlayerId(playerId))
            throw new DuplicateKeyException("player_id");
        Player player = playerService.findById(playerId);
        squad.setIsHuman(player.getIsHuman());
        Squad createdSquad = squadService.add(squad);
        joinSquad(createdSquad.getId(), playerId);
    }

    @Override
    public SquadMember findByPlayerId(int playerId) {
        return squadMemberRepository.findByPlayerId(playerId);
    }

}
