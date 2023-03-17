package noroff.project.hvz.services;

import jakarta.transaction.Transactional;
import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.AppUser;
import noroff.project.hvz.models.Player;
import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.models.dtos.SquadMemberWithPlayerNameDto;
import noroff.project.hvz.repositories.SquadMemberRepository;
import noroff.project.hvz.repositories.SquadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class SquadMemberServiceImpl implements  SquadMemberService{
    private final SquadRepository squadRepository;
    private final SquadMemberRepository squadMemberRepository;
    private final Logger logger = LoggerFactory.getLogger(SquadMemberServiceImpl.class);

    public SquadMemberServiceImpl(SquadMemberRepository squadMemberRepository, SquadRepository squadRepository){
        this.squadMemberRepository=squadMemberRepository;
        this.squadRepository=squadRepository;
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
            squadRepository.deleteById(squadId);
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
}
