package com.example.springpractice.controller;

import com.example.springpractice.entity.Payment;
import com.example.springpractice.enums.PaymentStatus;
import com.example.springpractice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @GetMapping(value = "/get-payments-by-date")
    public String getPaymentsByDate() {
        return "paymentsByDate";
    }

    @GetMapping(value = "/get-payments-by-period")
    public ModelAndView getByPaymentsDate(@RequestParam(name = "fromDate") String fromDate,
                                          @RequestParam(name = "toDate") String toDate) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("payments-list");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        modelAndView.addObject("payments",
                this.paymentService.getAllByPeriod(simpleDateFormat.parse(fromDate), simpleDateFormat.parse(toDate)));
        return modelAndView;
    }


    @RequestMapping(value = "/hello-payment", method = RequestMethod.POST)
    public String hello() {
        return "hello";
    }
}
