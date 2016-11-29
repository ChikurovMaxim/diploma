package com.news.dao;

import com.news.entities.Company;
import com.news.entities.Role;
import com.news.entities.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Maksym on 1/12/2016.
 */
@Local
public interface UserDAO {

    User savePerson(Long pId, User newPerson);

    User addCompanyToUser(Long pId, User user, Company company);

    User findPerson(Long id);

    void deletePerson(Long pId,User person);

    List getAll();

    Integer countPersons();

    List<User> findPersons(int startPosition, int maxResults, String sortFields, String sortDirections);

    User getUserIdByAuthData(String login, String password);

    User findUserByName(String name);

    List<User> findUsersByRole(Role role);
}
