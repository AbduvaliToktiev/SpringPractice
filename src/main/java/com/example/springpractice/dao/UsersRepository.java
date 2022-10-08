package com.example.springpractice.dao;

import com.example.springpractice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findFirstByEmail(String email);
}
