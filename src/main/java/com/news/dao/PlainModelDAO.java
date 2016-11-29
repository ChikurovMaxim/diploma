package com.news.dao;

import com.news.entities.Metric;
import com.news.entities.PlainModel;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Maksym on 28.11.2016.
 */
@Local
public interface PlainModelDAO {

    PlainModel savePlain(Long pId,PlainModel plainModel);

    void deletePlain(Long pId,Long plainModelId);

    List<PlainModel> findAll();

    PlainModel findPlainModel(Long plainModelId);

    PlainModel findPlainModel(String name);

    List<Metric> getPlainMetrics(PlainModel plainModel);
}
