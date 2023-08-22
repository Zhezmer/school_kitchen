package org.danikzhezmer.schoolkitchen.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "school_group")
public class SchoolGroup {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public SchoolGroup(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SchoolGroup() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SchoolGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
