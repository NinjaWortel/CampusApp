package be.ucll.CampusApplicatie.repository.Exception.CampusExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CampusBadRequestException extends RuntimeException {
    public CampusBadRequestException(String message) {
        super(message);
    }
}
