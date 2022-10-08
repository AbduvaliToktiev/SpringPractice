package com.example.springpractice.service;

import com.example.springpractice.dao.RoleRepository;
import com.example.springpractice.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoleByName(String name) {
        return this.roleRepository.findAllByRole(name);
    }
}
