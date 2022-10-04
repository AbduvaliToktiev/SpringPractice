package com.example.springpractice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDto {
    private String login;
    private String password;
    private String requisite;
    private BigDecimal amount;
}
