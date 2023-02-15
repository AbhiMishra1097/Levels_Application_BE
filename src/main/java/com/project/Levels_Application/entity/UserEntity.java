package com.project.Levels_Application.entity;

import com.project.Levels_Application.dto.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER_DETAILS")
public class UserEntity {
	
	@Id
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String phoneNo;

	public UserDTO convertToDTO(UserEntity userEntity){
		UserDTO user = new UserDTO(userEntity.getUsername(), userEntity.getPassword(), userEntity.getName()
		, userEntity.getEmail(), userEntity.getPhoneNo());

		return user;
	}

}
