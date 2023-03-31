package noroff.project.hvz.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateKeyException extends RuntimeException {
    /**
     * Exception for a situation where the key exists already.
     * @param key unique key name
     */
    public DuplicateKeyException(String key) {
        super("Conflict with the unique constraint: " + key);
    }
}

