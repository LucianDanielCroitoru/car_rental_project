package sda.academy.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sda.academy.entities.Car;
import sda.academy.util.HibernateUtil;

public class CarRepository {

    public void save(Car car) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    public void update(Car car) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.update(car);
        transaction.commit();
        session.close();
    }

    public void delete(Car car) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.delete(car);
        transaction.commit();
        session.close();
    }

    public Car findById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Car car = session.get(Car.class,id);
        session.getTransaction().commit();
        session.close();
        return car;
    }

    public void delete(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Car car = findById(id);
        if(car != null) {
            session.delete(car);
        }
        session.getTransaction().commit();
        session.close();
    }
}
