package com.murebackend.murebackend.User;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.*;

@Data
@Setter
@RequiredArgsConstructor
public class User {
	private Long id;

	@NonNull
	@NotBlank(message = "name is required")
	private String name;

	@NonNull
	@Email(message="Please provide a valid email address")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@NotBlank(message = "email is required")
	private String email;

	@NonNull
	@NotBlank(message = "password is required")
	private String password;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	private String imagePath;

	@Override
	public String toString() {
		return "User [email=" + email + ", id=" + id + ", name=" + name + "]";
	}
}
