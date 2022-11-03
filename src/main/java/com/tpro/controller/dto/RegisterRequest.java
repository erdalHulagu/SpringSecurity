package com.tpro.controller.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Setter;

@Data // Data icerisinde getter setter constructor ve benzeri annotationlar var zaten ondan koyduk
public class RegisterRequest {
   
	
	@NotBlank
	@NotNull
	@Size(min=1, max=15, message = "Your first name $'{validatedValue} must be beetween {min} and {max} chars long")
	private String firstName;
	
	@NotBlank
	@NotNull
	@Size(min=1, max=15, message = "Your last name $'{validatedValue} must be beetween {min} and {max} chars long")
	private String lastName;
	
	@NotBlank
	@NotNull
	@Size(min=1, max=15, message = "Your user name $'{validatedValue} must be beetween {min} and {max} chars long")
	private String userName;
	
	@NotBlank
	@NotNull
	@Size(min=1, max=15, message = "Your password$'{validatedValue} must be beetween {min} and {max} chars long")
	private String password;
	
	private Set<String> roles;
}
