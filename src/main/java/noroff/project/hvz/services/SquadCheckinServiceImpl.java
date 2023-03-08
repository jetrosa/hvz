package noroff.project.hvz.services;

import noroff.project.hvz.models.SquadCheckin;
import noroff.project.hvz.repositories.SquadCheckinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SquadCheckinServiceImpl implements SquadCheckinService{
    private final SquadCheckinRepository squadCheckinRepository;
    private final Logger logger = LoggerFactory.getLogger(SquadCheckinServiceImpl.class);
    public SquadCheckinServiceImpl(SquadCheckinRepository squadCheckinRepository){
        this.squadCheckinRepository=squadCheckinRepository;
    }

    @Override
    public SquadCheckin findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<SquadCheckin> findAll() {
        return null;
    }

    @Override
    public SquadCheckin add(SquadCheckin entity) {
        return null;
    }

    @Override
    public SquadCheckin update(SquadCheckin entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(SquadCheckin entity) {

    }
}
