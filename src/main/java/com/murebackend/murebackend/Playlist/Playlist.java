package com.murebackend.murebackend.Playlist;

import javax.validation.constraints.NotBlank;

import com.murebackend.murebackend.CommonProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Playlist  extends CommonProperties {
	@NonNull
	@NotBlank(message = "Title is required")
	private String title;	
	private boolean isPublic;
}
