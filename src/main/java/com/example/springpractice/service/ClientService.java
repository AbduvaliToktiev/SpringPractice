package com.example.springpractice.service;

import com.example.springpractice.dao.ClientRepository;
import com.example.springpractice.entity.Client;
import com.example.springpractice.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Boolean checkClient(String login, String password) {
        Client client = this.clientRepository.findByLoginAndPasswordAndStatusClient(login, password, Status.ACTIVE);
        return client != null;
    }

    public Client findByLogin(String login) {
        return this.clientRepository.findFirstByLoginAndStatusClient(login, Status.ACTIVE);
    }

    public void save(Client client) {
        this.clientRepository.save(client);
    }

    public void delete(Long id) {
        this.clientRepository.deleteById(id);
    }

    public List<Client> clients() {
        return this.clientRepository.findAll();
    }
}
