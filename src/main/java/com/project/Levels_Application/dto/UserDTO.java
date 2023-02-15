package com.project.Levels_Application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.Levels_Application.entity.UserEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Username should not be empty")
    @Length(min = 3, max = 15, message = "username length should be between 3 and 15")
    private String username;
    @NotBlank(message = "Password should not be null")
    @Length(min = 3, max = 15, message = "password length should be between 3 and 15")
    private String password;
    @NotBlank(message = "name should not be null")
    private String name;
    @NotBlank(message = "email should not be null")
    private String email;
    @NotBlank(message = "phone number should not be null")
    @Length(min=10, max=10, message = "Please enter a valid phone number")
    private String phoneNo;

    public UserEntity convertToEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity(userDTO.getUsername(), userDTO.getPassword(),
                userDTO.getName(), userDTO.getEmail(), userDTO.getPhoneNo());

        return userEntity;
    }

//    To ignore the password when we get the user object ....dont want to show the password to the client
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
