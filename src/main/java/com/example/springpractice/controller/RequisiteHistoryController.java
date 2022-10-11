package com.example.springpractice.controller;

import com.example.springpractice.entity.RequestHistory;
import com.example.springpractice.service.RequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RequisiteHistoryController {

    @Autowired
    private RequestHistoryService requestHistoryService;

    @GetMapping(value = "/get-all-reqHistory")
    public String getAll(Model model) {
        List<RequestHistory> requestHistories = requestHistoryService.getAllReqHistory();
        model.addAttribute("requestHistories", requestHistories);
        return "requisiteHistory-list";
    }

    @RequestMapping(value = "/hello-requisite-history", method = RequestMethod.POST)
    public String hello() {
        return "hello";
    }
}
