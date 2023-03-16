package noroff.project.hvz.services;

import noroff.project.hvz.models.Mission;

import java.util.Set;

public interface MissionService extends CrudService<Mission, Integer> {
    Set<Mission> findAllByGameId(final int gameId);
}
