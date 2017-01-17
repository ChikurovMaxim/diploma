package com.news.daoimpl;

import com.news.dao.PlainDao;
import com.news.dao.RecordDao;
import com.news.entities.PlainModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by Maksym on 17.01.2017.
 */
@Stateless
@EJB(name = "java:global/PlainDaoImpl",
        beanInterface = PlainDao.class, beanName = "PlainDaoImpl")
public class PlainDaoImpl implements PlainDao{
    @PersistenceContext(unitName = "UNIT")
    private EntityManager entityManager;

    @Override
    public Collection<PlainModel> getAll() {
        Query q = entityManager.createQuery("SELECT p FROM PlainModel p");
        return q.getResultList();
    }

    @Override
    public PlainModel getPlainById(int id) {
        Query q = entityManager.createQuery("select p FROM PlainModel p WHERE p.id = :id");
        q.setParameter("id",id);
        return (PlainModel) q.getSingleResult();
    }
}
