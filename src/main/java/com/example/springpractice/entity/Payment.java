package com.example.springpractice.entity;

import com.example.springpractice.enums.PaymentStatus;
import com.example.springpractice.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = Payment.TABLE_NAME)
public class Payment {

    public static final String TABLE_NAME = "PAYMENTS";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "create_date")
    private Date createDate;

    @Column
    @Enumerated(EnumType.STRING)
    PaymentStatus statusPayment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Client client;

    @Column(name = "requisite")
    private String requisitePayment;
}
