package com.coaching.soccer_lessons.repository;

import com.coaching.soccer_lessons.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {


}
