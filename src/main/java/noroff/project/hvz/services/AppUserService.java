package noroff.project.hvz.services;

import noroff.project.hvz.models.AppUser;

public interface AppUserService extends CrudService<AppUser, Integer> {
    boolean existsByUuid(final String uuid);
}
