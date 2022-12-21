package com.murebackend.murebackend.Role;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllRoles() {
        return new ResponseEntity<>(roleRepository.getAllRoles(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addRole(@Valid @RequestBody Role role) {
        try {
            Map<String, Object> response = new HashMap<>();
            roleRepository.save(new Role(role.getName()));
            response.put("message", role.getName() + " saved");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DuplicateKeyException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "That role already exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
