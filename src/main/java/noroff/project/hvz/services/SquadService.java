package noroff.project.hvz.services;

import noroff.project.hvz.models.Squad;

import java.util.Set;

public interface SquadService extends CrudService<Squad, Integer> {
    Squad updateKeepRelations(Squad updatedSquad);

    Set<Squad> findAllByGameId(final int gameId);
}
