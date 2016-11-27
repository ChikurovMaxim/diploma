package com.news.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Maksym on 1/12/2016.
 */
@Entity
@Table(name="plain_model")
public class PlainModel implements Serializable {

    public PlainModel() {
    }

    public PlainModel(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAIN_ID", unique = true, nullable = false)
    private Long id;

    private String name;

    @OneToMany (mappedBy = "plainModel")
    private Collection<Metric> metrics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlainModel that = (PlainModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
