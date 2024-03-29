package com.murebackend.murebackend.User;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.murebackend.murebackend.CommonProperties;
import com.murebackend.murebackend.Role.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User extends CommonProperties {

	@NonNull
	@NotBlank(message = "name is required")
	private String name;

	private String username;

	@NonNull
	@Email(message="Please provide a valid email address ")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@NotBlank(message = "email is required")
	private String email;
	private String password;

	private String imagePath;

	List<Role> roles;
}
