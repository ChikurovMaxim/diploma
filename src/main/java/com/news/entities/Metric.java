package com.news.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maksym on 27.11.2016.
 */

@Entity
@Table(name="metrics")
public class Metric  implements Serializable {

    public Metric() {
    }

    public Metric(String name, Double value, PlainModel plainModel) {
        this.name = name;
        this.value = value;
        this.plainModel = plainModel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "METRIC_ID", unique = true, nullable = false)
    private Long id;

    private String name;

    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAIN_ID")
    private PlainModel plainModel;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public PlainModel getPlainModel() {
        return plainModel;
    }

    public void setPlainModel(PlainModel plainModel) {
        this.plainModel = plainModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metric metric = (Metric) o;

        if (id != null ? !id.equals(metric.id) : metric.id != null) return false;
        if (name != null ? !name.equals(metric.name) : metric.name != null) return false;
        if (value != null ? !value.equals(metric.value) : metric.value != null) return false;
        return plainModel != null ? plainModel.equals(metric.plainModel) : metric.plainModel == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (plainModel != null ? plainModel.hashCode() : 0);
        return result;
    }
}
