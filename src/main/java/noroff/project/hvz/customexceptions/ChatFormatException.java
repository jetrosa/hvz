package noroff.project.hvz.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ChatFormatException extends RuntimeException {
    public ChatFormatException() {
        super("Invalid chat settings. All global visibility settings are false.");
    }
}
