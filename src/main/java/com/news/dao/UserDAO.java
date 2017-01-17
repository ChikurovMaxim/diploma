package com.news.dao;

import com.news.entities.Users;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Maksym on 1/12/2016.
 */
@Local
public interface UserDAO {

    Users findPerson(int id);

    Users getUserIdByAuthData(String login, String password);

}
