package com.murebackend.murebackend.Role;

import java.util.List;

public interface RoleRepository {
    int save(Role role);
    int update(Role role);
    Role findByName(String name);
    List<Role> getAllRoles();

}