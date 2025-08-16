package com.coaching.soccer_lessons.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

    public class LessonDTO {

        private Long id;

        @NotBlank(message = "Lesson name is required")
        private String name;

        @Positive(message = "Price must be greater than 0")
        private Double price;

        private String description;

        @Positive(message = "Duration must be greater than 0")
        private int duration;

        @NotNull(message = "Category ID is required")
        private Long categoryId;



        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }
    }



