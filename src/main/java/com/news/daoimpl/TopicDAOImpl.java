package com.news.daoimpl;

import com.news.dao.TopicDAO;
import com.news.entities.PlainModel;
import com.news.entities.Role;
import com.news.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * Created by Maksym on 1/12/2016.
 */
@Stateless
@EJB(name = "java:global/TopicDAOImpl",
        beanInterface = TopicDAO.class, beanName = "TopicDAOImpl")
public class TopicDAOImpl implements TopicDAO {

    @PersistenceContext(unitName = "UNIT2")
    private EntityManager entityManager;

    private boolean isAdmin(Long pId) {
        Query q = entityManager.createQuery("SELECT p FROM User p WHERE p.id=:pId");
        q.setParameter("pId", pId);
        User p = (User) q.getSingleResult();
        for (Role r : p.getRole()) {
            if (Objects.equals(r.getName(), "ADMIN")) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public PlainModel saveTopic(Long pId, PlainModel t) {
        if (isAdmin(pId)) {
            PlainModel plainModel = new PlainModel();
            if (t.getId() != null) {
                plainModel = t;
                entityManager.merge(plainModel);
            }
            else {
                plainModel.setName(t.getName());
                entityManager.persist(plainModel);
            }

            return plainModel;
        }
        else return null;
    }

    @Override
    @Transactional
    public void deleteTopic(Long personId, PlainModel t) {
        if(isAdmin(personId)){
            entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
        }
    }

    @Override
    public PlainModel findTopic(Long topicId) {
        return entityManager.find(PlainModel.class, topicId);
    }

    @Override
    public List<PlainModel> getAll() {
        Query q = entityManager.createQuery("SELECT t FROM Topic t");
        return q.getResultList();
    }

}
