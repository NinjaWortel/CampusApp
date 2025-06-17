package be.ucll.CampusApplicatie;

import be.ucll.CampusApplicatie.repository.Exception.CampusExceptions.CampusAlreadyExistsException;
import be.ucll.CampusApplicatie.repository.Exception.CampusExceptions.CampusBadRequestException;
import be.ucll.CampusApplicatie.repository.Exception.CampusExceptions.CampusNotFoundException;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomAlreadyExistsException;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomBadRequestException;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomNotFoundException;
import be.ucll.CampusApplicatie.repository.Exception.UserExceptions.UserAlreadyExistsException;
import be.ucll.CampusApplicatie.repository.Exception.UserExceptions.UserBadRequestException;
import be.ucll.CampusApplicatie.repository.Exception.UserExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GlobalEntityExceptionHandler {

    // CAMPUS

    @ExceptionHandler(CampusNotFoundException.class)
    public ResponseEntity<String> handleCampusNotFoundException(CampusNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Campus '" + ex.getMessage() + "' not found");
    }

    @ExceptionHandler(CampusAlreadyExistsException.class)
    public ResponseEntity<String> handleCampusAlreadyExistsException(CampusAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Campus '" + ex.getMessage() + "' already exists");
    }

    @ExceptionHandler(CampusBadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(CampusBadRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    // ROOM

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<String> handleRoomNotFoundException(RoomNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Room '" + ex.getMessage() + "' not found");
    }

    @ExceptionHandler(RoomAlreadyExistsException.class)
    public ResponseEntity<String> handleRoomAlreadyExistsException(RoomAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RoomBadRequestException.class)
    public ResponseEntity<String> handleRoomBadRequestException(RoomBadRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }


    // USER

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User with id " + ex.getMessage() + " not found");
    }

    @ExceptionHandler(UserBadRequestException.class)
    public ResponseEntity<String> handleBadRequest(UserBadRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleDuplicateUser(UserAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler({DateTimeParseException.class})
    public ResponseEntity<String> handleException() {
        return ResponseEntity
                .badRequest()
                .body("Date should be in format yyyy-MM-dd");
    }
}
