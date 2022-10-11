package com.example.springpractice.service;

import com.example.springpractice.dao.PaymentRepository;
import com.example.springpractice.dto.PaymentDto;
import com.example.springpractice.entity.Client;
import com.example.springpractice.entity.Payment;
import com.example.springpractice.enums.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ClientService clientService;

    public void save(PaymentDto paymentDto) {
        Client client = this.clientService.findByLogin(paymentDto.getLogin());
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setCurrency("KGS");
        payment.setRequisitePayment(paymentDto.getRequisite());
        payment.setClient(client);
        payment.setCreateDate(new Date());
        payment.setStatusPayment(PaymentStatus.SUCCESS);
        this.paymentRepository.save(payment);
    }

    public Payment findById(Long id) {
        return this.paymentRepository.findById(id).orElse(null);
    }

    public void update(Payment payment) {
        this.paymentRepository.save(payment);
    }

    public void delete(Long id) {
        this.paymentRepository.deleteById(id);
    }

    public List<Payment> paymentList(PaymentStatus success) {
        return this.paymentRepository.findAllByStatusPayment(PaymentStatus.SUCCESS);
    }

    public List<Payment> getAllByPeriod(Date fromDate, Date toDate) {
        return this.paymentRepository.getAllByPeriod(fromDate, toDate);
    }
}
