package com.murebackend.murebackend.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  class JdbcRoleRepository implements RoleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Role role) {
        String sql = "INSERT INTO roles(name) VALUES (?)";
        return jdbcTemplate.update(sql, role.getName());
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public Role findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM roles WHERE name = ?",
                BeanPropertyRowMapper.newInstance(Role.class), name);
    }

    @Override
    public List<Role> getAllRoles() {
        return jdbcTemplate.query("SELECT * FROM roles", BeanPropertyRowMapper.newInstance(Role.class));
    }
}