package com.example.demo.dao;

import com.example.demo.entites.Broker;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class BrokerDao implements IBrokerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Broker> getAllBrokers() {
        String hql = "FROM Broker as brk ORDER BY brk.id";
        return (List<Broker>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Broker getBrokerById(int brokerId) {
        return entityManager.find(Broker.class, brokerId);
    }

    @Override
    public void addBroker(Broker broker) {
        entityManager.persist(broker);
    }

    @Override
    public void updateBroker(int brokerId, Broker broker) {
        Broker brokerOld = getBrokerById(brokerId);
        if (brokerOld == null)
            return;
        brokerOld.setAge(broker.getAge() != null ? broker.getAge() : brokerOld.getAge());
        brokerOld.setName(broker.getName() != null ? broker.getName() : brokerOld.getName());
        brokerOld.setExperience(broker.getExperience() != null ?
                broker.getExperience() : brokerOld.getExperience());
        entityManager.flush();
    }

    @Override
    public void deleteBroker(int brokerId) {
        entityManager.remove(getBrokerById(brokerId));
    }

    @Override
    public boolean brokerExists(String title, int age, int experience) {
        String hql = "FROM Broker as brkr WHERE brkr.name = ?1 and brkr.age = ?2 and brkr.experience = ?3";
        int count = entityManager.createQuery(hql).setParameter(1, title)
                .setParameter(2, age).setParameter(3, experience).getResultList().size();
        return count > 0;
    }
}
