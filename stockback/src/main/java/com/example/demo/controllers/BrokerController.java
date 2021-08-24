package com.example.demo.controllers;

import com.example.demo.dao.BrokerDao;
import com.example.demo.dao.IBrokerDao;
import com.example.demo.entites.Broker;
import com.example.demo.services.IBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brokers")
public class BrokerController {

    @Autowired
    private IBrokerService brokerService;

    @GetMapping
    public ResponseEntity<List<Broker>> getBrokers() {
        return brokerService.getAllBrokers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Broker> getBroker(@PathVariable("id") int id) {
        return brokerService.getBroker(id);
    }

    @PostMapping
    public ResponseEntity createBroker(@RequestBody Broker body) {
        return brokerService.addBroker(body);
    }

    @PutMapping("{id}")
    public ResponseEntity updateBroker(@PathVariable("id") int id, @RequestBody Broker body) {
        return brokerService.updateBroker(id, body);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBroker(@PathVariable("id") int id) {
        return brokerService.deleteBroker(id);
    }
}
