package com.project.Levels_Application.service;

import com.project.Levels_Application.dto.UserDTO;
import com.project.Levels_Application.entity.UserEntity;
import com.project.Levels_Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> entity = userRepository.findById(username);

        if(!entity.isPresent()){
            throw new UsernameNotFoundException("Incorrect Username or Password");
        }
        return new User(entity.get().getUsername(), entity.get().getPassword(), new ArrayList<>());
    }

    public UserDTO getUserDTOByUsername(String username) throws UsernameNotFoundException{

        Optional<UserEntity>  userEntity = userRepository.findById(username);
        if(!userEntity.isPresent()){
            throw new UsernameNotFoundException("Incorrect Username or Password");
        }
        UserDTO user = userEntity.get().convertToDTO(userEntity.get());
        return user;

    }
}
