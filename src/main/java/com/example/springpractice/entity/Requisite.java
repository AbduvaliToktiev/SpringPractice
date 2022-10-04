package com.example.springpractice.entity;

import com.example.springpractice.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = Requisite.TABLE_NAME)
public class Requisite {

    public static final String TABLE_NAME = "REQUISITES";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQ_NAME)
    private Long id;

    @Column(name = "requisite")
    private String requisite;

    @Column(name = "fio")
    private String fio;

    @Column
    @Enumerated(EnumType.STRING)
    Status statusRequisite;
}
