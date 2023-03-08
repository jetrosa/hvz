package noroff.project.hvz.services;

import noroff.project.hvz.customexceptions.RecordNotFoundException;
import noroff.project.hvz.models.AppUser;
import noroff.project.hvz.repositories.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);

    public AppUserServiceImpl(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser findById(Integer id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> {
                            logger.warn("No user exists with ID: " + id);
                            return new RecordNotFoundException("App user", id);
                        }
                );
    }

    @Override
    public Collection<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser add(AppUser entity) {
        return appUserRepository.save(entity);
    }

    @Override
    public AppUser update(AppUser entity) {
        return appUserRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
        //todo player
    }
}
