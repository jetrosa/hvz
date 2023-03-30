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
    public ScheduleService(GameService gameService){
        this.gameService = gameService;
    }
    @Scheduled(fixedRate = 30000)
    public void gameStateService () {
        int infectionMinutes = 15;

        logger.info("scheduler running - checking game state");
        Collection<Game> games = gameService.findAll();

        for(Game g: games){
            OffsetDateTime startTime = g.getStartDateTime();
            OffsetDateTime endTime = g.getEndDateTime();
            switch (g.getGameState()){
                case REGISTRATION -> {
                    if(OffsetDateTime.now().isAfter(startTime))
                        gameService.setGameInfection(g);
                }
                case INFECTION -> {
                    if(OffsetDateTime.now().isAfter(startTime.plusSeconds(infectionMinutes))){
                        gameService.setGameStart(g);
                    }
                }
                case IN_PROGRESS -> {
                    if(OffsetDateTime.now().isAfter(endTime))
                        gameService.setGameComplete(g);
                }
            }
        }
        //get games
        //  check state
        //      not started -> check time -> game state: infection
        //      infection -> check start time+x -> game state: started, turn infected into zombies
        //      in progress -> check end time -> state:end
    }
}
