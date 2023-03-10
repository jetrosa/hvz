package noroff.project.hvz.services;

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
    public Kill findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<Kill> findAll() {
        return null;
    }

    @Override
    public Kill add(Kill entity) {
        return null;
    }

    @Override
    public Kill update(Kill entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Kill entity) {

    }
}
