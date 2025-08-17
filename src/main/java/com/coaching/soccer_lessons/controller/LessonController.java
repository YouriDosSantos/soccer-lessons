package com.coaching.soccer_lessons.controller;

import com.coaching.soccer_lessons.dto.LessonDTO;
import com.coaching.soccer_lessons.model.Lesson;
import com.coaching.soccer_lessons.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Return values in JSON
@RestController
@RequestMapping("/lessons") // sets a base path for all endpoints in this controller class
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    //CREATE -> with ResponseEntity we can customize the response in Postman e.g.,
    @PostMapping
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lessonDTO){
        LessonDTO created = lessonService.createLesson(lessonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<LessonDTO>> getAllLessons(){
        return ResponseEntity.ok(lessonService.getAllLessons());
    }

    //READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<LessonDTO> updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO){
        return ResponseEntity.ok(lessonService.updateLesson(id, lessonDTO));
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<LessonDTO> deleteLesson(@PathVariable Long id){
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }
}
