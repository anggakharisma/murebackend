package com.murebackend.murebackend.Song;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Song {
    private Long id;

    @NonNull
    @NotBlank(message = "name is required")
    private String name;

    @NonNull
    @NotBlank(message = "year is required")
    private int year;

    private String imagePath;
}
