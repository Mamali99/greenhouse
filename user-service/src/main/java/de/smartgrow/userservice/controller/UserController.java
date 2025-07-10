package de.smartgrow.userservice.controller;

import de.smartgrow.userservice.dto.UserRequestDTO;
import de.smartgrow.userservice.dto.UserResponseDTO;
import de.smartgrow.userservice.dto.validators.CreateUserValidationGroup;
import de.smartgrow.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "API for managing users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserResponseDTO> createUser(@Validated({Default.class, CreateUserValidationGroup.class}) @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO newUser = userService.createUser(userRequestDTO);
        return ResponseEntity.ok().body(newUser);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @Validated({Default.class}) @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO updatedUserResponseDTO = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok().body(updatedUserResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing user")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
