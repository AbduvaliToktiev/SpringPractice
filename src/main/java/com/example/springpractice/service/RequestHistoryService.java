package com.example.springpractice.service;

import com.example.springpractice.dao.RequestHistoryRepository;
import com.example.springpractice.entity.RequestHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestHistoryService {

    @Autowired
    private RequestHistoryRepository requestHistoryRepository;

    public void save(RequestHistory requestHistory) {
        this.requestHistoryRepository.save(requestHistory);
    }

    public List<RequestHistory> getAllReqHistory() {
        return this.requestHistoryRepository.findAll();
    }
}
