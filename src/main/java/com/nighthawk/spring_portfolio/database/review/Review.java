package com.nighthawk.spring_portfolio.database.review;

import java.util.ArrayList;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String reviewername;

    @Column
    private int age;

    @Column
    private String reviewtext;

    @Column
    private String contact;

    private Review() {

    }

    protected Review(Long id, String reviewername, int age, String reviewtext, String contact) {
        if (reviewername == null)
            throw new NullPointerException("reviewername");
        this.age = age;
        this.reviewtext = reviewtext;
        System.out.println(reviewername);
        this.reviewername = reviewername;
        this.contact = contact;
    }

    public String getReviewername() {
        return this.reviewername;
    }

    public int getAge() {
        return this.age;
    }

    public String getReviewtext() {
        return this.reviewtext;
    }

    public String getContact() {
        return this.contact;
    }

    public void setReviewername(String reviewername) {
        this.reviewername = reviewername;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setReviewtext(String reviewtext) {
        this.reviewtext = reviewtext;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public static void main(String[] args) {
        // Create a new review
        Review review = new Review(null, "Hetvi Trivedi", 17, "Great website! Very aesthetic", "hetvit27@gmail.com");

        // Print the review's properties
        System.out.println("Reviewer Name: " + review.getReviewername());
        System.out.println("Age: " + review.getAge());
        System.out.println("Review Text: " + review.getReviewtext());
        System.out.println("Contact: " + review.getContact());

        // Update the review's properties
        review.setReviewername("Jane Smith");
        review.setAge(30);
        review.setReviewtext("Excellent service!");
        review.setContact("jane.smith@example.com");

        // Print the updated review's properties
        System.out.println("Reviewer Name (Updated): " + review.getReviewername());
        System.out.println("Age (Updated): " + review.getAge());
        System.out.println("Review Text (Updated): " + review.getReviewtext());
        System.out.println("Contact (Updated): " + review.getContact());
    }
}
