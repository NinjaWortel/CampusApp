package be.ucll.CampusApplicatie.repository.Exception.RoomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoomCampusMismatchException extends RuntimeException {
    public RoomCampusMismatchException(String message) {
        super(message);
    }
}
