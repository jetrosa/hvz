package noroff.project.hvz.services;

import noroff.project.hvz.models.Bite;

import java.util.Set;

public interface BiteService extends CrudService<Bite, Integer> {
    Set<Bite> findAllByGameId(int gameId);
}
