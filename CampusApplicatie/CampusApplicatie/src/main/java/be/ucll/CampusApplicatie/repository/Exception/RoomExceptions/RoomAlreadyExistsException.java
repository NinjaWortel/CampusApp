package be.ucll.CampusApplicatie.repository.Exception.RoomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class RoomAlreadyExistsException extends RuntimeException {
    public RoomAlreadyExistsException(String message) {
        super(message);
    }
}
