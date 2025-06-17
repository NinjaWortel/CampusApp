package be.ucll.CampusApplicatie.controller;

import be.ucll.CampusApplicatie.DTO.UserDTO;
import be.ucll.CampusApplicatie.DTO.converter.DTOConverter;
import be.ucll.CampusApplicatie.entity.User;
import be.ucll.CampusApplicatie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = DTOConverter.convertDTOToUser(userDTO);
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(DTOConverter.convertToUserDTO(savedUser), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(required = false) String nameMatches) {
        List<User> users;

        if (nameMatches != null && !nameMatches.isBlank()) {
            users = userService.searchUsersByName(nameMatches);
        } else {
            users = userService.getAllUsers();
        }

        List<UserDTO> userDTOs = users.stream()
                .map(DTOConverter::convertToUserDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(DTOConverter.convertToUserDTO(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
