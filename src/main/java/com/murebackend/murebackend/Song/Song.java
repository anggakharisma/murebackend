package com.murebackend.murebackend.Song;

import com.murebackend.murebackend.CommonProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Song extends CommonProperties {
    @NonNull
    @NotBlank(message = "title is required")
    private String title;

    @NonNull
    @NotNull(message = "year is required")
    private Integer year;

    private String imagePath;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
