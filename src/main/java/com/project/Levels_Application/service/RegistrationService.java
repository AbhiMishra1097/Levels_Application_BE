package com.project.Levels_Application.service;

import com.project.Levels_Application.dto.UserDTO;
import com.project.Levels_Application.utilities.LevelsServiceException;

public interface RegistrationService {

    public Boolean registerUser(UserDTO userDTO) throws LevelsServiceException;
}

