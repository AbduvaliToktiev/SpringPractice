package com.example.springpractice.controller;

import com.example.springpractice.entity.RequestHistory;
import com.example.springpractice.entity.Requisite;
import com.example.springpractice.enums.Operations;
import com.example.springpractice.service.ClientService;
import com.example.springpractice.service.RequestHistoryService;
import com.example.springpractice.service.RequisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/requisite")
public class RequisiteController {

    @Autowired
    private RequisiteService requisiteService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RequestHistoryService requestHistoryService;

    @GetMapping(value = "/getRequisite")
    public ResponseEntity<Boolean> checkRequisite(@RequestParam(name = "login") String login,
                                                  @RequestParam(name = "password") String password,
                                                  @RequestParam(name = "requisite") String requisite) {
        RequestHistory requestHistory = new RequestHistory();
        requestHistory.setRequestDate(new Date());
        requestHistory.setRequestData(login + " " + password + " " + requisite);
        requestHistory.setOperations(Operations.GET_REQUISITE);
        boolean validate = this.clientService.checkClient(login, password);
        if (validate) {
            boolean validateRequisite = this.requisiteService.checkRequisite(requisite);
            if (validateRequisite) {
                requestHistory.setResponseData("TRUE " + HttpStatus.OK);
                this.requestHistoryService.save(requestHistory);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                requestHistory.setResponseData("FALSE " + HttpStatus.NOT_FOUND);
                this.requestHistoryService.save(requestHistory);
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        } else {
            requestHistory.setResponseData("FALSE " + HttpStatus.FORBIDDEN);
            this.requestHistoryService.save(requestHistory);
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/saveRequisite")
    public String save(@RequestBody Requisite requisite) {
        this.requisiteService.save(requisite);
        return "SUCCESS!";
    }


    @DeleteMapping(value = "/deleteRequisite")
    public String requisiteDelete(@RequestParam(name = "id") Long id) {
        try {
            this.requisiteService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "FAILED!";
        }
        return "SUCCESS";
    }

    @GetMapping(value = "/getAllRequisite")
    public List<Requisite> getAllRequisite() {
        return this.requisiteService.getAll();
    }
}
