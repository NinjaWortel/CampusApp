package be.ucll.CampusApplicatie.repository.Exception.CampusExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CampusAlreadyExistsException extends RuntimeException {
    public CampusAlreadyExistsException(String message) {
        super(message);
    }
}
