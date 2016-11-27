package com.news.entities;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maksym on 27.11.2016.
 */
@Entity
@Table(name="result")
public class Result implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    private User user;

    private PlainModel plainModel;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public User getUser() {
        return this.user;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public PlainModel getPlain() {
        return this.plainModel;
    }

    public Long getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlainModel getPlainModel() {
        return plainModel;
    }

    public void setPlainModel(PlainModel plainModel) {
        this.plainModel = plainModel;
    }
}
