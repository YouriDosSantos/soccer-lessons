package com.coaching.soccer_lessons.service;

import com.coaching.soccer_lessons.dto.LessonDTO;
import com.coaching.soccer_lessons.model.Category;
import com.coaching.soccer_lessons.model.Lesson;
import com.coaching.soccer_lessons.repository.CategoryRepository;
import com.coaching.soccer_lessons.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    //Set the dependencies down and final means that this variable cannot be changed
    private final LessonRepository lessonRepository;
    private final CategoryRepository categoryRepository;

    public LessonService(LessonRepository lessonRepository, CategoryRepository categoryRepository) {
        this.lessonRepository = lessonRepository;
        this.categoryRepository = categoryRepository;
    }

    //CREATE
    public LessonDTO createLesson(LessonDTO lessonDTO){
        Category category = categoryRepository.findById(lessonDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        Lesson lesson = new Lesson();
        lesson.setName(lessonDTO.getName());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setPrice(lessonDTO.getPrice());
        lesson.setDuration(lessonDTO.getDuration());
        lesson.setCategory(category);

        Lesson savedLesson = lessonRepository.save(lesson);

        return mapToLessonDTO(savedLesson);
    }

//    READ ALL
    public List<LessonDTO> getAllLessons(){
        return lessonRepository.findAll()
                .stream()
                .map(this::mapToLessonDTO)
                .collect(Collectors.toList());
    }

//  Read by ID
    public LessonDTO getLessonById(Long id){
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
        return mapToLessonDTO(lesson);
    }

//    Update -> PUT methods overwrite everything not just the specific value that needs changing.
    public LessonDTO updateLesson(Long id, LessonDTO lessonDTO) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));

        lesson.setName(lessonDTO.getName());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setPrice(lessonDTO.getPrice());
        lesson.setDuration(lessonDTO.getDuration());

        //CATEGORY UPDATE
        Category category = categoryRepository.findById(lessonDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        lesson.setCategory(category);

        Lesson updatedLesson = lessonRepository.save(lesson);
        return mapToLessonDTO(updatedLesson);
    }

//    DELETE
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

//    UTILITY MAPPING METHOD
    private LessonDTO mapToLessonDTO(Lesson lesson){
        LessonDTO dto = new LessonDTO();
        dto.setId(lesson.getId());
        dto.setName(lesson.getName());
        dto.setDescription(lesson.getDescription());
        dto.setPrice(lesson.getPrice());
        dto.setDuration(lesson.getDuration());
        dto.setCategoryId(lesson.getCategory().getId());
        return dto;
    }



}
