package com.murebackend.murebackend.Artist;

import com.murebackend.murebackend.CommonProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Artist extends CommonProperties {
	@NonNull
	@NotBlank(message = "name is required")
	private String name;

	@NonNull
	@NotBlank(message = "description is required")
	private String description;
	private String alias;
	private String imagePath;
}
