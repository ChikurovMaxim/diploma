package com.news.daoimpl;

import com.news.dao.CompanyDAO;
import com.news.entities.Company;
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
@EJB(name = "java:global/CompanyDAOImpl",
        beanInterface = CompanyDAO.class, beanName = "CompanyDAOImpl")
public class CompanyDAOImpl implements CompanyDAO {


    @PersistenceContext(unitName = "UNIT2")
    private EntityManager entityManager;

    private boolean isAdmin(Long pId){
        Query q = entityManager.createQuery("SELECT p FROM User p WHERE p.id=:pId");
        q.setParameter("pId",pId);
        User p = (User)q.getSingleResult();
        return Objects.equals(p.getRole(), Role.ADMIN);
    }

    @Override
    @Transactional
    public Company saveCompany(Long personId , Company company) {
        Company c1;
        if (isAdmin(personId)) {
            if(company.getId() == null){
                c1 = new Company(company.getName());
                entityManager.persist(c1);
            }
            else {
                c1 = company;
                entityManager.merge(c1);
            }
            return c1;
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteCompany(Long personId, Company company) {
        if (isAdmin(personId)) {
            entityManager.remove(entityManager.contains(company) ? company : entityManager.merge(company));
        }
    }

    @Override
    public List<Company> getAll() {
        Query q = entityManager.createQuery("SELECT c FROM Company c");
        return q.getResultList();
    }

    @Override
    public Company findCompanyByName(String name) {
        Query q = entityManager.createQuery("SELECT a FROM Company a WHERE a.date = :name");
        q.setParameter("name",name);
        return (Company)q.getSingleResult();
    }

    @Override
    public List<User> getCompanyUsers(Company company) {
        return (List<User>) company.getUsers();
    }

    @Override
    public Company findCompany(Long id) {
        Query q = entityManager.createQuery("SELECT n FROM Company n WHERE n.id = :id");
        q.setParameter("id", id);
        return (Company)q.getSingleResult();
    }
}
