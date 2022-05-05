package com.vieira.sogolon.consumer.service;

import com.vieira.sogolon.consumer.model.Student;
import com.vieira.sogolon.consumer.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        studentRepository.save(student);
        return student;
    }

    public List<Student> getListStudent() {
        return studentRepository.findAll();
    }

}
