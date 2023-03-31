package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.Squad;
import noroff.project.hvz.repositories.SquadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class SquadServiceImpl implements SquadService {
    private final SquadRepository squadRepository;
    private final Logger logger = LoggerFactory.getLogger(SquadServiceImpl.class);

    public SquadServiceImpl(SquadRepository squadRepository) {
        this.squadRepository = squadRepository;
    }

    @Override
    public Squad findById(Integer id) {
        return squadRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No squad exists with ID: " + id);
                            return new RecordNotFoundException("Squad", id);
                        }
                );
    }

    @Override
    public Collection<Squad> findAll() {
        return squadRepository.findAll();
    }

    @Override
    public Squad add(Squad entity) {
        return squadRepository.save(entity);
    }

    @Override
    public Squad update(Squad entity) {
        return squadRepository.save(entity);
    }

    @Override
    public Squad updateKeepRelations(Squad updatedSquad) {
        Squad oldSquad = findById(updatedSquad.getId());
        updatedSquad.setSquadCheckins(oldSquad.getSquadCheckins());
        updatedSquad.setSquadMembers(oldSquad.getSquadMembers());
        updatedSquad.setChatMessages(oldSquad.getChatMessages());
        return squadRepository.save(updatedSquad);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(Squad squad) {
        squadRepository.delete(squad);
    }

    @Override
    public Set<Squad> findAllByGameId(int gameId) {
        return squadRepository.findAllByGameId(gameId);
    }
}
