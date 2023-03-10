package noroff.project.hvz.services;

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
    public SquadMember findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<SquadMember> findAll() {
        return null;
    }

    @Override
    public SquadMember add(SquadMember entity) {
        return null;
    }

    @Override
    public SquadMember update(SquadMember entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(SquadMember entity) {

    }
}
