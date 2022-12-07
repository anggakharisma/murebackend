package com.murebackend.murebackend.Album;

import com.murebackend.murebackend.Model;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Album extends Model {

    @NonNull
    @NotBlank(message = "Title is required")
    private String title;

    private String imagePath;
}
