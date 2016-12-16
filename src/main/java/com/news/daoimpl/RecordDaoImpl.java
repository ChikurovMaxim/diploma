package com.news.daoimpl;


import com.news.dao.RecordDao;
import com.news.entities.Record;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * Created by Maksym on 14.12.2016.
 */
@Stateless
@EJB(name = "java:global/RecordDaoImpl",
        beanInterface = RecordDao.class, beanName = "RecordDaoImpl")
public class RecordDaoImpl implements RecordDao{

    @PersistenceContext(unitName = "UNIT")
    private EntityManager entityManager;

    @Override
    public List<Record> getAllRecords() {
        Query q = entityManager.createQuery("SELECT r FROM Record r");
        return q.getResultList();
    }

    @Override
    public Record findRecord(int rId) {
        try {
            Query q = entityManager.createQuery("SELECT r FROM Record r WHERE r.Id = :id");
            q.setParameter("id", rId);
            return (Record) q.getSingleResult();
        }catch (NoResultException e ){
            return null;
        }
    }

    @Override
    public List<Record> findRecordsByUser(String userName) {
        Query q = entityManager.createQuery("SELECT r FROM Record r WHERE r.user = :user_name");
        q.setParameter("user_name" , userName);
        return q.getResultList();
    }

    @Override
    public List<Record> findRecordsByDate(Date date) {
        Query q = entityManager.createQuery("SELECT r FROM Record r WHERE r.date = :date");
        q.setParameter("date" , date);
        return q.getResultList();
    }
}

