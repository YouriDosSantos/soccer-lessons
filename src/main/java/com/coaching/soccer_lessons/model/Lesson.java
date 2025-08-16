package com.coaching.soccer_lessons.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private int duration;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category is Required")
    private Category category;

    public Lesson(){

    }

    public Lesson(Long id, String name, double price, String description, int duration, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.duration = duration;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


            //According to reseach toString is no longer needed to the entity since we have a dto now

//    @Override
//    public String toString() {
//        return "Lesson{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", duration=" + duration +
//                ", price=" + price +
//                '}';
//    }
}
