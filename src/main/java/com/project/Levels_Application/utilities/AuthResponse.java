package com.project.Levels_Application.utilities;

import com.project.Levels_Application.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String jwt;
    private UserDTO user;



}
