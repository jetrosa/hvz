package noroff.project.hvz.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidSquadException extends RuntimeException {
    /**
     * Player is not the target squad and the action is unauthorized.
     * @param playerId player database ID
     * @param squadId squad database ID
     */
    public InvalidSquadException(int playerId, int squadId) {
        super("Forbidden. Player " + playerId + " is not in squad: " + squadId);
    }
}
