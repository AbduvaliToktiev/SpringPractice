package com.example.springpractice.controller;

import com.example.springpractice.entity.Client;
import com.example.springpractice.enums.Status;
import com.example.springpractice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/create-client")
    public String create(Model model) {
        model.addAttribute("client", new Client());
        return "client";
    }

    @PostMapping(value = "/save-client")
    public String save(@ModelAttribute(name = "client") Client client) {
        client.setStatusClient(Status.ACTIVE);
        clientService.save(client);
        return "hello";
    }

    @GetMapping(value = "/get-all-client")
    public String getAll(Model model) {
        List<Client> clients = clientService.allClient();
        model.addAttribute("clients", clients);
        return "client-list";
    }

//    @GetMapping(path = "/modalSaveClient")
//    private String submitEmployee(Model model) {
//        ModelAndView modelAndView = new ModelAndView("client-list");
//        List<Client> clientTest = new ArrayList<>();
//        model.addAttribute("clientTest", clientTest);
//        List<Client> clients = clientService.allClient();
//        model.addAttribute("clients", clients);
//        return "hello";
//    }

    @RequestMapping(value = "/hello-client", method = RequestMethod.POST)
    public String hello() {
        return "hello";
    }
}
