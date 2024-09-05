package sda.academy.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sda.academy.entities.Station;
import sda.academy.util.HibernateUtil;

import java.util.List;

public class StationRepository {
    public void save(Station station) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.save(station);
        transaction.commit();
        session.close();
    }

    public void update(Station station) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.update(station);
        transaction.commit();
        session.close();
    }

    public void delete(Station station) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.delete(station);
        transaction.commit();
        session.close();
    }

    public List<Station> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();

        String hql = "from Station";
        String hql2 = "SELECT s from Station s";
        Query<Station> query = session.createQuery(hql, Station.class);
        List<Station> stations = query.getResultList();

        transaction.commit();
        session.close();
        return stations;
    }

    public Station findById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Station station = session.get(Station.class,id);
        session.getTransaction().commit();
        session.close();
        return station;
    }

    public void delete(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Station station = findById(id);
        if(station != null) {
            session.delete(station);
        }
        session.getTransaction().commit();
        session.close();
    }
}
