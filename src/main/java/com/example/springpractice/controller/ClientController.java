package com.example.springpractice.controller;

import com.example.springpractice.entity.Client;
import com.example.springpractice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/register")
    public String save(@RequestBody Client client) {
        this.clientService.save(client);
        return "SUCCESS!";
    }

    @DeleteMapping(value = "/deleteClient")
    public String delete(@RequestParam(name = "id") Long id) {
        try {
            this.clientService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "FAILED!";
        }
        return "SUCCESS!";
    }

    @GetMapping("/getAllClient")
    public List<Client> getAllClients() {
        return this.clientService.clients();
    }
}
