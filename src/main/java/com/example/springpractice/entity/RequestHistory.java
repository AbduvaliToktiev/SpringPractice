package com.example.springpractice.entity;

import com.example.springpractice.enums.Operations;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = RequestHistory.TABLE_NAME)
public class RequestHistory {

    public static final String TABLE_NAME = "REQUEST_HISTORIES";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Operations operations;

    @Column
    private String requestData;

    @Column
    private String responseData;

    @Column
    private Date requestDate;
}
