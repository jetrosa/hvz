package noroff.project.hvz.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String name, long id){
        super(name + " does not exist with ID: " + id);
    }
}
