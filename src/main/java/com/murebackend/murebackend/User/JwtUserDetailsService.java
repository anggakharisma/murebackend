package com.murebackend.murebackend.User;

import java.util.ArrayList;
import java.util.List;

import com.murebackend.murebackend.Role.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.murebackend.murebackend.User.User user = userRepository.findByEmail(email);
        List<Role> roles = userRepository.getUserRoles(user);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        roles.forEach(item -> authorityList.add(new SimpleGrantedAuthority(item.getName())));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                authorityList);
    }
}
