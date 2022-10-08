package com.example.springpractice.controller;

import com.example.springpractice.entity.Requisite;
import com.example.springpractice.enums.Status;
import com.example.springpractice.service.ClientService;
import com.example.springpractice.service.RequestHistoryService;
import com.example.springpractice.service.RequisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RequisiteController {

    @Autowired
    private RequisiteService requisiteService;

    @GetMapping(value = "/create-requisite")
    public String create(Model model) {
        model.addAttribute("requisite", new Requisite());
        return "requisite";
    }

    @PostMapping(value = "/requisite-save")
    public String save(@ModelAttribute(name = "requisite") Requisite requisite) {
        requisite.setStatusRequisite(Status.ACTIVE);
        requisiteService.save(requisite);
        return "hello";
    }

    @GetMapping(value = "/get-all-requisite")
    public String getAll(Model model) {
        List<Requisite> requisites = requisiteService.getAll();
        model.addAttribute("requisites", requisites);
        return "requisite-list";
    }

    @RequestMapping(value = "/hello-requisite", method = RequestMethod.POST)
    public String hello() {
        return "hello";
    }
}
