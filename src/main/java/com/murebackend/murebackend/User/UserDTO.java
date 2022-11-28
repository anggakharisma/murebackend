package com.murebackend.murebackend.User;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String imagePath;
}
