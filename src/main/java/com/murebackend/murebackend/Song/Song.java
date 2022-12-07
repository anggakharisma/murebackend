package com.murebackend.murebackend.Song;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Song {
    private Long id;

    @NonNull
    @NotBlank(message = "title is required")
    private String title;

    @NonNull
    @NotNull(message = "year is required")
    private Integer year;

    private String imagePath;
}
