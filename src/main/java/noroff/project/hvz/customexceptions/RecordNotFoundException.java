package noroff.project.hvz.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
    /**
     * Record not found in the database.
     * @param name the requested object
     * @param id the ID used for searching
     */
    public RecordNotFoundException(String name, long id) {
        super(name + " does not exist with ID: " + id);
    }

    /**
     * Record not found in the database.
     * @param name the requested object
     * @param id the ID used for searching
     */
    public RecordNotFoundException(String name, String id) {
        super(name + " does not exist with ID: " + id);
    }
}
