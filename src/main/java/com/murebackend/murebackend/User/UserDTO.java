package com.murebackend.murebackend.User;

import com.murebackend.murebackend.Role.Role;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String imagePath;
    private List<Role> roles;
}
