package be.ucll.CampusApplicatie.repository.Exception.CampusExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CampusNotFoundException extends RuntimeException {
    public CampusNotFoundException(String name) {
        super(name);
    }
}
