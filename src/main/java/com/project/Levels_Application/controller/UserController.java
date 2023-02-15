package com.project.Levels_Application.controller;

import com.project.Levels_Application.dto.UserDTO;
import com.project.Levels_Application.service.MyUserDetailsService;
import com.project.Levels_Application.service.RegistrationService;
import com.project.Levels_Application.utilities.AuthResponse;
import com.project.Levels_Application.utilities.JwtUtil;
import com.project.Levels_Application.utilities.LevelsServiceException;
import jakarta.validation.Valid;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.project.Levels_Application.dto.LoginDTO;


@RestController
@RequestMapping("/Levels")
@CrossOrigin
public class UserController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "/login" ,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthResponse> authenticateUser(@RequestBody @Valid LoginDTO loginDTO) throws LevelsServiceException {
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		}
		catch (BadCredentialsException e){
			throw new LevelsServiceException("Incorrect Username or Password");
		}

		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginDTO.getUsername());
		final UserDTO user = myUserDetailsService.getUserDTOByUsername(loginDTO.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		//so that the user's password is not visible in the response
//		user.setPassword(null);

		AuthResponse authResponse = new AuthResponse(jwt, user);

		return ResponseEntity.ok(authResponse);
	}

	@PostMapping("/register")
	public ResponseEntity<Boolean> registerUser(@RequestBody @Valid UserDTO userDTO) throws LevelsServiceException{
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		Boolean result = registrationService.registerUser(userDTO);

		return ResponseEntity.ok(result);
	}


}
