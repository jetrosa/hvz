package noroff.project.hvz.services;

import noroff.project.hvz.models.Squad;
import noroff.project.hvz.repositories.SquadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SquadServiceImpl implements SquadService{
    private final SquadRepository squadRepository;
    private final Logger logger = LoggerFactory.getLogger(SquadServiceImpl.class);
    public SquadServiceImpl(SquadRepository squadRepository){
        this.squadRepository=squadRepository;
    }
    @Override
    public Squad findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<Squad> findAll() {
        return null;
    }

    @Override
    public Squad add(Squad entity) {
        return null;
    }

    @Override
    public Squad update(Squad entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Squad entity) {

    }
}
