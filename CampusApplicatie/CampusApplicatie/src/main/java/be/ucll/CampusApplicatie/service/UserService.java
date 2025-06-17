package be.ucll.CampusApplicatie.service;

import be.ucll.CampusApplicatie.entity.User;
import be.ucll.CampusApplicatie.repository.Exception.UserExceptions.UserAlreadyExistsException;
import be.ucll.CampusApplicatie.repository.Exception.UserExceptions.UserBadRequestException;
import be.ucll.CampusApplicatie.repository.Exception.UserExceptions.UserNotFoundException;
import be.ucll.CampusApplicatie.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        validateUser(user);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> searchUsersByName(String name) {
        return userRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    public User getUserEntityById(Long id) {
        return getUserById(id);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new UserBadRequestException("User cannot be null");
        }
        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            throw new UserBadRequestException("First name is mandatory");
        }
        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw new UserBadRequestException("Last name is mandatory");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new UserBadRequestException("Email is mandatory");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with email '" + user.getEmail() + "' already exists");
        }
    }
}
