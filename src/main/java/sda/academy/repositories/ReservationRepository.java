package sda.academy.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sda.academy.entities.Reservation;
import sda.academy.util.HibernateUtil;

import java.util.List;

public class ReservationRepository {
    public void save(Reservation reservation) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.save(reservation);
        transaction.commit();
        session.close();
    }

    public void update(Reservation reservation) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.update(reservation);
        transaction.commit();
        session.close();
    }

    public void delete(Reservation reservation) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.delete(reservation);
        transaction.commit();
        session.close();
    }

    public Reservation findById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Reservation reservation = session.get(Reservation.class,id);
        session.getTransaction().commit();
        session.close();
        return reservation;
    }

    public List<Reservation> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();

        String hql = "from Reservation";
        String hql2 = "SELECT c from Reservation c";
        Query<Reservation> query = session.createQuery(hql, Reservation.class);
        List<Reservation> reservations = query.getResultList();

        transaction.commit();
        session.close();
        return reservations;
    }

    public void delete(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Reservation reservation = findById(id);
        if(reservation != null) {
            session.delete(reservation);
        }
        session.getTransaction().commit();
        session.close();
    }
}
