package com.murebackend.murebackend.Role;

import org.springframework.stereotype.Repository;

@Repository
public  class JdbcRoleRepository implements RoleRepository {

    @Override
    public int save(Role role) {
        return 0;
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public Role findByName(String name) {
        return null;
    }
}