package de.smartgrow.userservice.service;

import de.smartgrow.userservice.dto.UserRequestDTO;
import de.smartgrow.userservice.dto.UserResponseDTO;
import de.smartgrow.userservice.exception.EmailAlreadyExistsException;
import de.smartgrow.userservice.exception.UserNotFoundException;
import de.smartgrow.userservice.mapper.UserMapper;
import de.smartgrow.userservice.model.User;
import de.smartgrow.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDTO).toList();

    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        if(userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A user with this Email "  + userRequestDTO.getEmail() + " already exists");
        }
        User newUser = userRepository.save(UserMapper.toEntity(userRequestDTO));
        return UserMapper.toDTO(newUser);
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID " + id));
        if(userRepository.existsByEmailAndIdNot(userRequestDTO.getEmail(), id)){
            throw new EmailAlreadyExistsException("A user with this Email "  + userRequestDTO.getEmail() + " already exists");
        }
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        //user.setPassword(userRequestDTO.getPassword()); we're not allowed to change the password in this way!!! we should handle it in a different way
        User updatedUser = userRepository.save(user);
        return UserMapper.toDTO(updatedUser);
    }

    public void deleteUser(UUID id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found with ID " + id);
        }
    }

}
