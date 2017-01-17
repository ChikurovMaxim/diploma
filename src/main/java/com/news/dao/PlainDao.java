package com.news.dao;

import com.news.entities.PlainModel;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by Maksym on 17.01.2017.
 */
@Local
public interface PlainDao {
    Collection<PlainModel> getAll();

    PlainModel getPlainById(int id);

}
