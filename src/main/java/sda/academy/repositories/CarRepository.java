package sda.academy.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sda.academy.entities.Car;
import sda.academy.entities.Station;
import sda.academy.util.HibernateUtil;

import java.util.List;

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

    public List<Car> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();

        String hql = "from Car";
        String hql2 = "SELECT c from Car c";
        Query<Car> query = session.createQuery(hql, Car.class);
        List<Car> cars = query.getResultList();

        transaction.commit();
        session.close();
        return cars;
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

    public List<Car> findAllCarsByStation(int station_id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();

        String hql = "SELECT c from Car c WHERE station_id = :parameter";
        Query<Car> query = session.createQuery(hql, Car.class);
        query.setParameter("parameter",station_id);
        List<Car> cars = query.getResultList();

        transaction.commit();
        session.close();
        return cars;
    }
}
