package com.ForumJavaWS.demo.rest.entity;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {


    @Id
    @GeneratedValue
    private Long id;

    private String name;




    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
