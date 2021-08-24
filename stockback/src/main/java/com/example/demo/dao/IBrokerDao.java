package com.example.demo.dao;

import com.example.demo.entites.Broker;

import java.util.List;

public interface IBrokerDao {
    List<Broker> getAllBrokers();
    Broker getBrokerById(int brokerId);
    void addBroker(Broker broker);
    void updateBroker(int brokerId, Broker broker);
    void deleteBroker(int brokerId);
    boolean brokerExists(String name, int age, int experience);
}
