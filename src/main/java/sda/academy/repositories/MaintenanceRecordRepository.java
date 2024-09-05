package sda.academy.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sda.academy.entities.MaintenanceRecord;
import sda.academy.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class MaintenanceRecordRepository {
    public void save(MaintenanceRecord maintenanceRecord) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.save(maintenanceRecord);
        transaction.commit();
        session.close();
    }

    public void update(MaintenanceRecord maintenanceRecord) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.update(maintenanceRecord);
        transaction.commit();
        session.close();
    }

    public MaintenanceRecord findById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        MaintenanceRecord maintenanceRecord = session.get(MaintenanceRecord.class,id);
        session.getTransaction().commit();
        session.close();
        return maintenanceRecord;
    }

    public List<MaintenanceRecord> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.getTransaction();

        String hql = "from MaintenanceRecord";
        String hql2 = "SELECT m from MaintenanceRecord m";
        Query<MaintenanceRecord> query = session.createQuery(hql, MaintenanceRecord.class);
        List<MaintenanceRecord> maintenanceRecords = query.getResultList();

        transaction.commit();
        session.close();
        return maintenanceRecords;
    }

    public MaintenanceRecord validate(LocalDate formatterStartDate, LocalDate formatterEndDate) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "SELECT m FROM MaintenanceRecord m WHERE m.maintenanceDate BETWEEN :formatterStartDate AND :formatterEndDate";
        Query<MaintenanceRecord> query = session.createQuery(hql, MaintenanceRecord.class);
        query.setParameter("formatterStartDate",formatterStartDate);
        query.setParameter("formatterEndDate",formatterEndDate);
        List<MaintenanceRecord> maintenanceRecordList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (maintenanceRecordList.isEmpty()) {
            return null;
        } else {
            return maintenanceRecordList.get(0);
        }
    }
}