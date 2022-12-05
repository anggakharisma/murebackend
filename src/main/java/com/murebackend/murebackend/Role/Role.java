package com.murebackend.murebackend.Role;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;

    @NonNull
    @NotBlank(message = "name is required")
    private String name;
}