package com.example.springpractice.dao;

import com.example.springpractice.entity.Client;
import com.example.springpractice.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByLoginAndPasswordAndStatusClient(String login, String password, Status statusClient);

    Client findFirstByLoginAndStatusClient(String login, Status statusClient);
}
