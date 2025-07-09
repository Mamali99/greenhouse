package de.smartgrow.userservice.controller;

import de.smartgrow.userservice.dto.UserRequestDTO;
import de.smartgrow.userservice.dto.UserResponseDTO;
import de.smartgrow.userservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO newUser = userService.createUser(userRequestDTO);
        return ResponseEntity.ok().body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @Validated({Default.class}) @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO updatedUserResponseDTO = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok().body(updatedUserResponseDTO);
    }
}
