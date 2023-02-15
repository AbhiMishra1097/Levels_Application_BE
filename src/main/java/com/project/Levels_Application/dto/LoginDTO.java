package com.project.Levels_Application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
	@NotBlank(message = "Username should not be Blank")

	private String username;

	@NotBlank(message = "Password should not be Blank")
	private String password;

}
