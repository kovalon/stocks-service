package com.example.demo.services;

import com.example.demo.entites.Broker;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBrokerService {
    ResponseEntity getAllBrokers();
    ResponseEntity getBroker(int brokerId);
    ResponseEntity addBroker(Broker broker);
    ResponseEntity updateBroker(int brokerId, Broker broker);
    ResponseEntity deleteBroker(int brokerId);

}
