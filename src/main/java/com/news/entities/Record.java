package com.news.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Maksym on 14.12.2016.
 */
@Entity
@Table(name = "records")
public class Record implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID", unique = true, nullable = false)
    private int Id;

    @Column(name = "USER_NAME")
    private String user;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "SIM_DATA")
    private String simData;

    @Column(name = "PLAIN_MODEL")
    private String plainModel;

    public Record() {
    }

    public Record(LocalDateTime date, String simData, String user, String plainModel) {
        this.date = date;
        this.user = user;
        this.plainModel = plainModel;
    }

    public int getId() {
        return Id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSimData() {
        return simData;
    }

    public void setSimData(String simData) {
        this.simData = simData;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPlainModel() {
        return plainModel;
    }

    public void setPlainModel(String plainModel) {
        this.plainModel = plainModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (Id != record.Id) return false;
        if (date != null ? !date.equals(record.date) : record.date != null) return false;
        if (simData != null ? !simData.equals(record.simData) : record.simData != null) return false;
        if (user != null ? !user.equals(record.user) : record.user != null) return false;
        return plainModel != null ? plainModel.equals(record.plainModel) : record.plainModel == null;

    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (simData != null ? simData.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (plainModel != null ? plainModel.hashCode() : 0);
        return result;
    }
}
