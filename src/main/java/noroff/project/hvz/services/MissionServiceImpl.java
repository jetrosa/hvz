package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.Mission;
import noroff.project.hvz.repositories.MissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepository;
    private final Logger logger = LoggerFactory.getLogger(MissionServiceImpl.class);
    public MissionServiceImpl(MissionRepository missionRepository){
        this.missionRepository=missionRepository;
    }
    @Override
    public Mission findById(Integer id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No mission exists with ID: " + id);
                            return new RecordNotFoundException("Mission", id);
                        }
                );
    }

    @Override
    public Collection<Mission> findAll() {
        return missionRepository.findAll();
    }

    @Override
    public Mission add(Mission entity) {
        return missionRepository.save(entity);
    }

    @Override
    public Mission update(Mission entity) {
        return missionRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(Mission mission) {
        missionRepository.delete(mission);
    }

    @Override
    public Set<Mission> findAllByGameId(int gameId) {
        return missionRepository.findAllByGameId(gameId);
    }
}
