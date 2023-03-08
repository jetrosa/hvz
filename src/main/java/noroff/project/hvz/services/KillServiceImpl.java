package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.Kill;
import noroff.project.hvz.repositories.KillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class KillServiceImpl implements KillService{
    private final KillRepository killRepository;
    private final Logger logger = LoggerFactory.getLogger(KillServiceImpl.class);
    public KillServiceImpl(KillRepository killRepository){
        this.killRepository=killRepository;
    }
    @Override
    public Kill findById(Integer id) {
        return killRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No kill exists with ID: " + id);
                            return new RecordNotFoundException("Kill", id);
                        }
                );
    }

    @Override
    public Collection<Kill> findAll() {
        return killRepository.findAll();
    }

    @Override
    public Kill add(Kill entity) {
        return killRepository.save(entity);
    }

    @Override
    public Kill update(Kill entity) {
        return killRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(Kill kill) {
        killRepository.delete(kill);
    }
}
