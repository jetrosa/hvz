package noroff.project.hvz.scheduled;

import noroff.project.hvz.models.Game;
import noroff.project.hvz.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Collection;

@Component
public class ScheduleService {
    private final Logger logger = LoggerFactory.getLogger(ScheduleService.class);
    private final GameService gameService;

    public ScheduleService(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Periodically find all games in the database and check their game state.
     * <p>
     * - game is not started -> check the time -> game state: infection, define a random patient zero player<p>
     * - game state infection -> check start time + x -> game state: started, turn infected into zombies<p>
     * - game in progress -> check end time -> game state:end<p>
     */
    @Scheduled(fixedRate = 30000)
    public void gameStateService() {
        int infectionMinutes = 15; //infection state duration, after that the patient zero turns into a zombie

        logger.info("scheduler running - checking game state");
        Collection<Game> games = gameService.findAll();

        for (Game g : games) {
            OffsetDateTime startTime = g.getStartDateTime();
            OffsetDateTime endTime = g.getEndDateTime();
            switch (g.getGameState()) {
                case REGISTRATION -> {
                    if (OffsetDateTime.now().isAfter(startTime))
                        gameService.setGameInfection(g);
                }
                case INFECTION -> {
                    if (OffsetDateTime.now().isAfter(startTime.plusSeconds(infectionMinutes))) {
                        gameService.setGameStart(g);
                    }
                }
                case IN_PROGRESS -> {
                    if (OffsetDateTime.now().isAfter(endTime))
                        gameService.setGameComplete(g);
                }
            }
        }
    }
}
