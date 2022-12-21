package com.murebackend.murebackend.Album;

import com.murebackend.murebackend.CommonProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Album extends CommonProperties {

    @NonNull
    @NotBlank(message = "Title is required")
    private String title;

    private String imagePath;
}
