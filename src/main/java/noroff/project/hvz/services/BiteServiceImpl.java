package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.DuplicateKeyException;
import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.Bite;
import noroff.project.hvz.repositories.BiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class BiteServiceImpl implements BiteService {
    private final BiteRepository biteRepository;
    private final Logger logger = LoggerFactory.getLogger(BiteServiceImpl.class);
    public BiteServiceImpl(BiteRepository biteRepository){
        this.biteRepository = biteRepository;
    }
    @Override
    public Bite findById(Integer id) {
        return biteRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No kill exists with ID: " + id);
                            return new RecordNotFoundException("Kill", id);
                        }
                );
    }

    @Override
    public Collection<Bite> findAll() {
        return biteRepository.findAll();
    }

    @Override
    public Bite add(Bite bite) {
        boolean isVictimAlreadyBitten = biteRepository.existsByVictimId(bite.getVictim().getId());
        if(!isVictimAlreadyBitten)
            return biteRepository.save(bite);
        else
            throw new DuplicateKeyException("victim");
    }

    @Override
    public Bite update(Bite entity) {
        return biteRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(Bite bite) {
        biteRepository.delete(bite);
    }

    @Override
    public Set<Bite> findAllByGameId(int gameId) {
        return biteRepository.findAllByGameId(gameId);
    }
}
