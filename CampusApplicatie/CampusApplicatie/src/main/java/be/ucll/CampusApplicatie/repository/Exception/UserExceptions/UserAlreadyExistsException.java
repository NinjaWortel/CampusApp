package be.ucll.CampusApplicatie.repository.Exception.UserExceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
