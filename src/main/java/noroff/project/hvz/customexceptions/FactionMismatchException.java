package noroff.project.hvz.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class FactionMismatchException extends RuntimeException {
    public FactionMismatchException(boolean isPlayerHuman, String target) {
        super(String.format("Player faction does not match the target faction. Player: %s, %s: %s", isPlayerHuman ? "human" : "zombie", target, !isPlayerHuman ? "human" : "zombie"));
    }
}
