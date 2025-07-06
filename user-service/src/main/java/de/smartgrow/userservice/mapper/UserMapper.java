package de.smartgrow.userservice.mapper;

import de.smartgrow.userservice.dto.UserResponseDTO;
import de.smartgrow.userservice.model.User;

public class UserMapper {

    public static UserResponseDTO toDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId().toString());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
