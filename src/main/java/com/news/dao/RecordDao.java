package com.news.dao;

import com.news.entities.Record;
import com.news.entities.Users;

import javax.ejb.Local;
import java.sql.Date;
import java.util.List;

/**
 * Created by Maksym on 14.12.2016.
 */
@Local
public interface RecordDao {

    Record findRecord(int rId);

    List<Record> findRecordsByUser(Users userName);
}