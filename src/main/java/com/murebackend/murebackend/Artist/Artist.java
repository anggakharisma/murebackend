package com.murebackend.murebackend.Artist;

import com.murebackend.murebackend.Model;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Artist extends Model {
	@NonNull
	@NotBlank(message = "name is required")
	private String name;

	@NonNull
	@NotBlank(message = "description is required")
	private String description;
	private String alias;
	private String imagePath;
}
