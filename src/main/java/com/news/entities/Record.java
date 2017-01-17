package com.news.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Maksym on 14.12.2016.
 */
@Entity
@Table(name = "records")
public class Record implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int Id;

    @ManyToOne(targetEntity = Users.class,fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID",referencedColumnName = "ID",insertable = false)
    private Users user;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "SIM_DATA")
    private String simData;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="PLAIN_MODEL_ID", referencedColumnName = "ID",insertable = false)
    private PlainModel plainModel;

    public Record() {
    }

    public int getId() {
        return Id;
    }

    public Users getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public String getSimData() {
        return simData;
    }

    public PlainModel getPlainModel() {
        return plainModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (Id != record.Id) return false;
        if (user != null ? !user.equals(record.user) : record.user != null) return false;
        if (date != null ? !date.equals(record.date) : record.date != null) return false;
        if (simData != null ? !simData.equals(record.simData) : record.simData != null) return false;
        return plainModel != null ? plainModel.equals(record.plainModel) : record.plainModel == null;

    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (simData != null ? simData.hashCode() : 0);
        result = 31 * result + (plainModel != null ? plainModel.hashCode() : 0);
        return result;
    }
}
