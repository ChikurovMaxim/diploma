package com.news.daoimpl;

import com.news.dao.UserDAO;
import com.news.entities.Users;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Maksym on 1/12/2016.
 */
@Stateless
@EJB(name = "java:global/UserDAOImpl",
        beanInterface = UserDAO.class, beanName = "UserDAOImpl")
public class UserDAOImpl implements UserDAO {


    @PersistenceContext(unitName = "UNIT2")
    private EntityManager entityManager;

    @Override
    public Users findPerson(int id){
        if(id != 0)return entityManager.find(Users.class,id);
        else return null;
    }

    @Override
    public Users findUserByName(String name) {
        Query q = entityManager.createQuery("SELECT u FROM Users u WHERE u.name = :uname");
        q.setParameter("uname", name);
        return (Users)q.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Users> findUsersByRole(String role){
        return getAll().stream()
                .filter(u -> u.getRole().equals(role))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Users> getAll() {
        Query q = entityManager.createQuery("select p from Users p");
        return q.getResultList();
    }

    @Override
    public Users getUserIdByAuthData(String login, String password) {
        try {
            Query q = entityManager.createQuery("select p from Users p where p.login = :login and p.password=:passwd");
            q.setParameter("login", login);
            q.setParameter("passwd", password);
            return (Users) q.getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
}
