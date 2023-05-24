package com.nighthawk.spring_portfolio.database.matches;

import java.util.ArrayList;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private int age;

    @Column
    private String location;

    @Column
    private String pronouns;

    private Matches() {

    }

    protected Matches(Long id, String name, int age, String location, String pronouns) {
        if (name == null)
            throw new NullPointerException("name");
        this.age = age;
        this.location = location;
        System.out.println(name);
        this.name = name;
        this.pronouns = pronouns;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getLocation() {
        return this.location;
    }

    public String getPronouns() {
        return this.pronouns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }
}
