package com.sks.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "rating")
    private double rating;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "review_date")
    private Date reviewDate;

    // Constructors, getters, and setters

    // Constructors

    public Review() {
    }

    public Review(Long customerId, Long restaurantId, double rating, String comments, Date reviewDate) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comments = comments;
        this.reviewDate = reviewDate;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}