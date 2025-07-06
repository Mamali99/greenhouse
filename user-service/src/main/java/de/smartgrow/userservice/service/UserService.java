package de.smartgrow.userservice.service;

import de.smartgrow.userservice.dto.UserRequestDTO;
import de.smartgrow.userservice.dto.UserResponseDTO;
import de.smartgrow.userservice.exception.EmailAlreadyExistsException;
import de.smartgrow.userservice.mapper.UserMapper;
import de.smartgrow.userservice.model.User;
import de.smartgrow.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
