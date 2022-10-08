package com.example.springpractice.restController;

import com.example.springpractice.dto.PaymentDto;
import com.example.springpractice.entity.Payment;
import com.example.springpractice.entity.RequestHistory;
import com.example.springpractice.enums.Operations;
import com.example.springpractice.enums.PaymentStatus;
import com.example.springpractice.service.ClientService;
import com.example.springpractice.service.PaymentService;
import com.example.springpractice.service.RequestHistoryService;
import com.example.springpractice.service.RequisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentControllerRest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RequisiteService requisiteService;

    @Autowired
    private RequestHistoryService requestHistoryService;

    @PostMapping(value = "/pay")
    public ResponseEntity<Boolean> pay(@RequestBody PaymentDto paymentDto) {
        RequestHistory requestHistory = new RequestHistory();
        requestHistory.setRequestDate(new Date());
        requestHistory.setRequestData(paymentDto.getLogin() + " " + paymentDto.getPassword() + " " + paymentDto.getRequisite());
        requestHistory.setOperations(Operations.PAY);
        boolean validClient = this.clientService.checkClient(paymentDto.getLogin(), paymentDto.getPassword());
        if (validClient) {
            boolean validRequisite = this.requisiteService.checkRequisite(paymentDto.getRequisite());
            if (validRequisite) {
                this.paymentService.save(paymentDto);
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

    @PutMapping(value = "/rollback")
    public ResponseEntity<Boolean> rollback(@RequestParam(name = "paymentId") Long paymentId) {
        RequestHistory requestHistory = new RequestHistory();
        requestHistory.setRequestDate(new Date());
        requestHistory.setOperations(Operations.ROLLBACK);
        Payment payment = this.paymentService.findById(paymentId);
        if (payment != null) {
            payment.setStatusPayment(PaymentStatus.ROLLBACK);
            this.paymentService.update(payment);
            requestHistory.setResponseData("TRUE " + HttpStatus.OK);
            requestHistory.setRequestData("SUCCESS");
            this.requestHistoryService.save(requestHistory);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            requestHistory.setResponseData("FALSE " + HttpStatus.NOT_FOUND);
            requestHistory.setRequestData("ERROR");
            this.requestHistoryService.save(requestHistory);
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletePayment")
    public String delete(@RequestParam(name = "id") Long id) {
        try {
            this.paymentService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "FAILED!";
        }
        return "SUCCESS!";
    }

    @GetMapping(value = "/getAllPayment")
    public List<Payment> getAllPayment() {
        return this.paymentService.paymentList(PaymentStatus.SUCCESS);
    }
}
