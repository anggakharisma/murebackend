package com.murebackend.murebackend.Role;

import com.murebackend.murebackend.Role.Role;

public interface RoleRepository {
    int save(Role role);
    int update(Role role);
    Role findByName(String name);

}