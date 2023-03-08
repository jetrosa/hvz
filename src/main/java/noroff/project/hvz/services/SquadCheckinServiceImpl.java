package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.RecordNotFoundException;
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
    public SquadCheckin findById(Integer id) {
        return squadCheckinRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No squad checkin exists with ID: " + id);
                            return new RecordNotFoundException("Squad checkin", id);
                        }
                );
    }

    @Override
    public Collection<SquadCheckin> findAll() {
        return squadCheckinRepository.findAll();
    }

    @Override
    public SquadCheckin add(SquadCheckin entity) {
        return squadCheckinRepository.save(entity);
    }

    @Override
    public SquadCheckin update(SquadCheckin entity) {
        return squadCheckinRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(SquadCheckin squadCheckin) {
        squadCheckinRepository.delete(squadCheckin);
        //todo chatmessage
    }
}
