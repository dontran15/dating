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

    @Column
    private String socials;

    private Matches() {

    }

    protected Matches(Long id, String name, int age, String location, String pronouns, String socials) {
        if (name == null)
            throw new NullPointerException("name");
        this.age = age;
        this.location = location;
        System.out.println(name);
        this.name = name;
        this.pronouns = pronouns;
        this.socials = socials;
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

    public String getSocials() {
        return this.socials;

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

    public void setSocials(String socials) {
        this.socials = socials;
    }

    public static void main(String[] args) {
        // Create a new review
        Matches matches = new Matches(null, "Iris Yang", 17, "san diego", "she/her", "@iriisyang");

        // Print the review's properties
        System.out.println("Name: " + matches.getName());
        System.out.println("Age: " + matches.getAge());
        System.out.println("Location: " + matches.getLocation());
        System.out.println("Pronouns: " + matches.getPronouns());
        System.out.println("Socials: " + matches.getSocials());

        // Update the review's properties
        matches.setName("Iris Yang");
        matches.setAge(17);
        matches.setLocation("San Diego");
        matches.setPronouns("she/her");
        matches.setSocials("@iriisyang");

        // Print the updated review's properties
        System.out.println("Name (Updated): " + matches.getName());
        System.out.println("Age (Updated): " + matches.getAge());
        System.out.println("Location(Updated): " + matches.getLocation());
        System.out.println("Pronouns (Updated): " + matches.getPronouns());
        System.out.println("Socials (Updated): " + matches.getSocials());
    }
}
