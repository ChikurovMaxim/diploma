package com.news.dao;

import com.news.entities.Company;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Maksym on 1/12/2016.
 */
@Local
public interface ArticleDAO {

    Company saveArticle(Long pId, Company company);

    void deleteArticle(Long personId, Company company);

    Company findArticle(Long aId);

    List<Company> getAll();

    List<Company> findArticlesForDate(String date);

    List<Company> findArticlesForCreator(Long crtId);

    Company changeArticlesCreator(Long pId, Long articleId, Long crtrId);
}
