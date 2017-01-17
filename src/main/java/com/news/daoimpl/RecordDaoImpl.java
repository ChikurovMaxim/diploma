package com.news.daoimpl;


import com.news.dao.RecordDao;
import com.news.entities.Record;
import com.news.entities.Users;

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
    public Record findRecord(int rId) {
        Query q = entityManager.createQuery("SELECT r FROM Record r where r.Id = :id");
        q.setParameter("id",rId);
        return (Record) q.getSingleResult();
    }

    @Override
    public List<Record> findRecordsByUser(Users userName) {
        Query q = entityManager.createQuery("SELECT r FROM Record r WHERE r.user = :user");
        q.setParameter("user",userName);
        return q.getResultList();
    }
}

