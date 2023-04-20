package com.murebackend.murebackend.Song;

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
public class Song extends CommonProperties {
    @NonNull
    @NotBlank(message = "title is required")
    private String title;
    @NotNull
    private Integer year;

    private String imagePath;
    private String audioPath;

    @NotNull
    private Integer artistId;
    @NotNull
    private Integer albumId;
}
