package com.example.springpractice.service;

import com.example.springpractice.dao.RequisiteRepository;
import com.example.springpractice.entity.Requisite;
import com.example.springpractice.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisiteService {

    @Autowired
    private RequisiteRepository requisiteRepository;

    public Boolean checkRequisite(String requisite) {
        Requisite requisite1 = this.requisiteRepository.findFirstByRequisiteAndStatusRequisite(requisite, Status.ACTIVE);
        return requisite1 != null;
    }

    public void save(Requisite requisite) {
        this.requisiteRepository.save(requisite);
    }

    public void delete(Long id) {
        this.requisiteRepository.deleteById(id);
    }

    public List<Requisite> getAll() {
        return this.requisiteRepository.findAll();
    }

}
