package com.news.daoimpl;

import com.news.dao.UserDAO;
import com.news.entities.Company;
import com.news.entities.Role;
import com.news.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    private boolean isAdmin(Long pId) {
        Query q = entityManager.createQuery("SELECT p FROM User p WHERE p.id=:pId");
        q.setParameter("pId", pId);
        User p = (User) q.getSingleResult();
        return Objects.equals(p.getRole(), Role.ADMIN);
    }
    @Override
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
    public User addCompanyToUser(Long pId,User user, Company company){
        if(user.getId()!=null && company.getId()!=null){
            user.setCompany(company);
            entityManager.merge(user);
        }
        return null;
    }

    @Override
    public User findPerson(Long id){
        if(id != null)return entityManager.find(User.class,id);
        else return null;
    }

    @Override
    public User findUserByName(String name) {
        Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :uname");
        q.setParameter("uname", name);
        return (User)q.getSingleResult();
    }

    @Override
    public List<User> findUsersByRole(Role role){
        return getAll().stream()
                .filter(u -> u.getRole().getRole().equals(role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePerson(Long pId, User person) {
        if (isAdmin(pId)) {
            User newP = entityManager.find(User.class, person.getId());
            if (newP != null) entityManager.remove(newP);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll() {
        Query q = entityManager.createQuery("select p from User p");
        return q.getResultList();
    }

    @Override
    public Integer countPersons() {
        Query query = entityManager.createQuery("SELECT COUNT(p.id) FROM User p");
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findPersons(int startPosition, int maxResults, String sortFields, String sortDirections) {
        List<User> pList = new ArrayList<>();
        Query query = entityManager.createQuery("SELECT p FROM User p ORDER BY " + sortFields + " " + sortDirections);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        pList.addAll(((List<User>) query.getResultList())
                .stream().filter(p -> !p.getName().equals("UNDEFINED")).collect(Collectors.toList()));
        return pList;
    }

    @Override
    public User getUserIdByAuthData(String login, String password) {
        try {
            Query q = entityManager.createQuery("select p from User p where p.login = :login and p.password=:passwd");
            q.setParameter("login", login);
            q.setParameter("passwd", password);
            return (User) q.getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
}
