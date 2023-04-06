package com.murebackend.murebackend.Album;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Year is required")
    private int year;

    private String imagePath;
}
