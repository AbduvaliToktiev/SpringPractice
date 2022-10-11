package com.example.springpractice.dao;

import com.example.springpractice.entity.Payment;
import com.example.springpractice.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Modifying
    @Transactional
    @Query(value = "update payments set amount = :amount, " +
            "curency = :curency, " +
            "date = current_timestamp, " +
            "requisite = :requisite, " +
            "status_payment = :status_payment " +
            "where id = :id", nativeQuery = true)
    void updatePayment(@Param(value = "amount") Integer amount,
                       @Param(value = "curency") String curency,
                       @Param(value = "requisite") String requisite,
                       @Param(value = "status_payment") String paymentStatus,
                       @Param(value = "id") Long id);

    @Query(value = "select * from simple_web_program.payments " +
            "where create_date between :fromDate and :toDate", nativeQuery = true)
    List<Payment> getAllByPeriod(@Param(value = "fromDate") Date fromDate,
                                 @Param(value = "toDate") Date toDate);

    List<Payment> findAllByStatusPayment(PaymentStatus success);
}
