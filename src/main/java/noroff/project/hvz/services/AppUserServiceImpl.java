package noroff.project.hvz.services;

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
    public AppUser findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<AppUser> findAll() {
        return null;
    }

    @Override
    public AppUser add(AppUser entity) {
        return null;
    }

    @Override
    public AppUser update(AppUser entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(AppUser entity) {

    }
}
