package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.repositories.SquadMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SquadMemberServiceImpl implements  SquadMemberService{
    private final SquadMemberRepository squadMemberRepository;
    private final Logger logger = LoggerFactory.getLogger(SquadMemberServiceImpl.class);

    public SquadMemberServiceImpl(SquadMemberRepository squadMemberRepository){
        this.squadMemberRepository=squadMemberRepository;
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

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(SquadMember squadMember) {
        squadMemberRepository.delete(squadMember);
    }
}
