package noroff.project.hvz.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidSquadException extends RuntimeException{
    public InvalidSquadException(int playerId, int squadId) {
        super("Forbidden. Player "+ playerId + " is not in squad: " + squadId);
    }
}
