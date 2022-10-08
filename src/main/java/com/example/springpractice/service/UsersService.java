package com.example.springpractice.service;

import com.example.springpractice.dao.UsersRepository;
import com.example.springpractice.entity.Role;
import com.example.springpractice.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    public void save(Users user) {
        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findFirstByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role r : roles) {
            authorities.add(new SimpleGrantedAuthority(r.getRole()));
        }

        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
