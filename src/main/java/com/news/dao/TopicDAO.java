package com.news.dao;

import com.news.entities.PlainModel;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Maksym on 1/12/2016.
 */
@Local
public interface TopicDAO {

    PlainModel saveTopic(Long pId, PlainModel t);

    void deleteTopic(Long personId, PlainModel t);

    PlainModel findTopic(Long topicId);

    List<PlainModel> getAll();
}
