package com.murebackend.murebackend.Role;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Role {
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}