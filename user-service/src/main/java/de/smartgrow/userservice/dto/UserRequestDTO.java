package de.smartgrow.userservice.dto;

import de.smartgrow.userservice.dto.validators.CreateUserValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username can not be longer than 100 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email should be valid")
    private String email;

    @NotBlank(groups = CreateUserValidationGroup.class, message = "Password is required")
    private String password;

    @NotBlank(groups = CreateUserValidationGroup.class, message = "Registered date is required")
    private String registeredDate;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
