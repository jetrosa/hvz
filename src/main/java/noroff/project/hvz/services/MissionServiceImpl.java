package noroff.project.hvz.services;

import noroff.project.hvz.models.Mission;
import noroff.project.hvz.repositories.KillRepository;
import noroff.project.hvz.repositories.MissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepository;
    private final Logger logger = LoggerFactory.getLogger(MissionServiceImpl.class);
    public MissionServiceImpl(MissionRepository missionRepository){
        this.missionRepository=missionRepository;
    }
    @Override
    public Mission findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<Mission> findAll() {
        return null;
    }

    @Override
    public Mission add(Mission entity) {
        return null;
    }

    @Override
    public Mission update(Mission entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Mission entity) {

    }
}
