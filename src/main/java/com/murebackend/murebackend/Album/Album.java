package com.murebackend.murebackend.Album;

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
public class Album extends CommonProperties {

    @NonNull
    @NotBlank(message = "Title is required")
    private String title;

		@NonNull
    @NotBlank(message = "year is required")
    private String year;

    private String imagePath;
}
