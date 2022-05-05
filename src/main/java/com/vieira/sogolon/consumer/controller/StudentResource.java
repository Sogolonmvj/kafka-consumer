package com.vieira.sogolon.consumer.controller;

import com.vieira.sogolon.consumer.model.Student;
import com.vieira.sogolon.consumer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentResource {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> saveMessage(@RequestBody Student student){

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        student.setCreated(now);

        studentService.save(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getListStudent(){
        List<Student> studentList = studentService.getListStudent();
        return ResponseEntity.ok(studentList);
    }
}
