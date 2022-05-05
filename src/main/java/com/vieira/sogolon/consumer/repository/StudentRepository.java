package com.vieira.sogolon.consumer.repository;

import com.vieira.sogolon.consumer.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    public Student save(Student student);
}
