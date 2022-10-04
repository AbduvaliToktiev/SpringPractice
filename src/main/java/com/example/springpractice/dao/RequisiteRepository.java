package com.example.springpractice.dao;

import com.example.springpractice.entity.Requisite;
import com.example.springpractice.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisiteRepository extends JpaRepository<Requisite, Long> {

    Requisite findFirstByRequisiteAndStatusRequisite(String requisite, Status statusRequisite);

}
