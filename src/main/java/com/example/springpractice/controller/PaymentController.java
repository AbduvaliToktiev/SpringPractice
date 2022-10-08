package com.example.springpractice.controller;

import com.example.springpractice.entity.Payment;
import com.example.springpractice.enums.PaymentStatus;
import com.example.springpractice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/all-payments")
    public String allPayments(Model model) {
        List<Payment> payments = paymentService.paymentList(PaymentStatus.SUCCESS);
        model.addAttribute("payments", payments);
        return "payments-list";
    }
}
