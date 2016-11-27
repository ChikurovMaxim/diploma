package com.news.dao;

import com.news.entities.Company;
import com.news.entities.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Maksym on 1/12/2016.
 */
@Local
public interface CompanyDAO {

    Company saveCompany(Long pId, Company company);

    void deleteCompany(Long personId, Company company);

    Company findCompany(Long aId);

    List getAll();

    Company findCompanyByName(String name);

    List<User> getCompanyUsers(Company company);

}
