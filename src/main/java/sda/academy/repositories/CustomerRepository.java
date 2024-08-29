package sda.academy.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sda.academy.entities.Customer;
import sda.academy.util.HibernateUtil;

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
