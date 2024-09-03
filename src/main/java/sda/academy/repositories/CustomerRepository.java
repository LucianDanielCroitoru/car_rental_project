package sda.academy.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sda.academy.entities.Car;
import sda.academy.entities.Customer;
import sda.academy.util.HibernateUtil;

import java.util.List;

public class CustomerRepository {

    public void save(Customer customer) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public void update(Customer customer) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    public void delete(Customer customer) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.delete(customer);
        transaction.commit();
        session.close();
    }

    public Customer findById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class,id);
        session.getTransaction().commit();
        session.close();
        return customer;
    }

    public List<Customer> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();

        String hql = "from Customer";
        String hql2 = "SELECT c from Customer c";
        Query<Customer> query = session.createQuery(hql, Customer.class);
        List<Customer> customers = query.getResultList();

        transaction.commit();
        session.close();
        return customers;
    }

    public void delete(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Customer customer = findById(id);
        if(customer != null) {
            session.delete(customer);
        }
        session.getTransaction().commit();
        session.close();
    }
}
