package com.deloitte.jobacceptance.dao;

import com.deloitte.jobacceptance.model.Customer;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> findAll() {
        Session session = sessionFactory.openSession();
        List<Customer> customers = session.createCriteria(Customer.class).list();
        session.close();
        return customers;    }

    @Override
    public Customer findById(Long id) {
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class,id);
        session.close();
        return customer;    }

    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(customer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class,id);
        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }
}
