package com.news.entities;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Maksym on 1/12/2016.
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {
    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID", unique = true, nullable = false)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "company")
    private Collection<User> users;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != null ? !id.equals(company.id) : company.id != null) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        return users != null ? users.equals(company.users) : company.users == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }
}