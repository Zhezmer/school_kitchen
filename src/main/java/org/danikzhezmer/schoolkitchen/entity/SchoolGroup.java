package org.danikzhezmer.schoolkitchen.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "school_group")
public class SchoolGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
