package com.example.demo.services;

import com.example.demo.dao.IBrokerDao;
import com.example.demo.entites.Broker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerService implements IBrokerService {
    @Autowired
    private IBrokerDao brokerDao;

    @Override
    public ResponseEntity getAllBrokers(){
        try {
            List<Broker> result = brokerDao.getAllBrokers();
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-type", "application/json")
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-type", "application/json")
                    .body("{\"status\": \"Internal Server Error\"}");
        }
    }

    @Override
    public ResponseEntity getBroker(int brokerId) {
        try {
            Broker result = brokerDao.getBrokerById(brokerId);
            if (result == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("Content-type", "application/json")
                        .body("{\"status\": \"NOT FOUND\"}");
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-type", "application/json")
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-type", "application/json")
                    .body("{\"status\": \"Internal Server Error\"}");
        }

    }

    @Override
    public ResponseEntity addBroker(Broker broker) {
        if (brokerDao.brokerExists(broker.getName(), broker.getAge(), broker.getExperience()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Content-type", "application/json")
                    .body("{\"status\": \"BAD REQUEST\"," +
                            "\"message\": \"the broker already exists\"}");
        try {
            brokerDao.addBroker(broker);
             return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Content-type", "application/json")
                    .body("{\"status\": \"OBJET SUCCESSFULLY CREATED\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-type", "application/json")
                    .body("{\"status\": \"Internal Server Error\"}");
        }
    }

    @Override
    public ResponseEntity updateBroker(int brokerId, Broker broker) {
        try {
            if (brokerDao.getBrokerById(brokerId) == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("Content-type", "application/json")
                        .body("{\"status\": \"NOT FOUND\"}");
            brokerDao.updateBroker(brokerId, broker);
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-type", "application/json")
                    .body("{\"status\": \"OBJECT SUCCESSFULLY UPDATED\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-type", "application/json")
                    .body("{\"status\": \"Internal Server Error\"}");
        }
    }

    @Override
    public ResponseEntity deleteBroker(int brokerId) {
        try {
            if (brokerDao.getBrokerById(brokerId) == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("Content-type", "application/json")
                        .body("{\"status\": \"NOT FOUND\"}");
            brokerDao.deleteBroker(brokerId);
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-type", "application/json")
                    .body("{\"status\": \"OBJECT SUCCESSFULLY DELETED\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-type", "application/json")
                    .body("{\"status\": \"Internal Server Error\"}");
        }
    }
}
