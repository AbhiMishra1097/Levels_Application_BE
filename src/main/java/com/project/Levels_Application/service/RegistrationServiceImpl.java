package com.project.Levels_Application.service;

import com.project.Levels_Application.dto.UserDTO;
import com.project.Levels_Application.entity.UserEntity;
import com.project.Levels_Application.repository.UserRepository;
import com.project.Levels_Application.utilities.LevelsServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public Boolean registerUser(UserDTO userDTO) throws LevelsServiceException {
        UserEntity userEntity = userDTO.convertToEntity(userDTO);
        if(userRepository.findById(userDTO.getUsername()).isPresent())
        {
            throw new LevelsServiceException("User with given Username already exists");
        }
        else{
            userRepository.saveAndFlush(userEntity);
            return true;
        }

    }
}
