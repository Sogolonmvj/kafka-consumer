package com.vieira.sogolon.consumer.repository;

import com.vieira.sogolon.consumer.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    public Student save(Student student);
    Optional<Student> findStudentByEmail(String email);
}
