package com.news.daoimpl;

import com.news.dao.PlainModelDAO;
import com.news.entities.Metric;
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
 * Created by Maksym on 29.11.2016.
 */
@Stateless
@EJB(name = "java:global/TopicDAOImpl",
        beanInterface = PlainModelDAO.class, beanName = "PlainModelDAOImpl")
public class PlainModelDAOImpl implements PlainModelDAO {

    @PersistenceContext(unitName = "UNIT2")
    private EntityManager entityManager;

    private boolean isAdmin(Long pId) {
        Query q = entityManager.createQuery("SELECT p FROM User p WHERE p.id=:pId");
        q.setParameter("pId", pId);
        User p = (User) q.getSingleResult();
        return Objects.equals(p.getRole(), Role.ADMIN);
    }
    @Override
    @Transactional
    public PlainModel savePlain(Long pId, PlainModel plainModel) {
        entityManager.createQuery()
        PlainModel pm = new PlainModel(plainModel.getName());
        return null;
    }

    @Transactional
    public User savePerson(Long pid, User newPerson) {
        User u = new User(newPerson.getName(),newPerson.getRole(),
                newPerson.getLogin(),newPerson.getPassword());
        if(isAdmin(pid)) {
            if (newPerson.getId() == null) {
                entityManager.persist(u);
            } else {
                u.setId(newPerson.getId());
                entityManager.merge(u);
            }
        }
        return u;
    }

    @Override
    public void deletePlain(Long pId, Long plainModelId) {

    }

    @Override
    public List<PlainModel> findAll() {
        return null;
    }

    @Override
    public PlainModel findPlainModel(Long plainModelId) {
        return null;
    }

    @Override
    public PlainModel findPlainModel(String name) {
        return null;
    }

    @Override
    public List<Metric> getPlainMetrics(PlainModel plainModel) {
        return null;
    }
}
