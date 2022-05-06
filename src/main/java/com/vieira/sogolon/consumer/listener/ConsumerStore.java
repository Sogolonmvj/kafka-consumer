package com.vieira.sogolon.consumer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vieira.sogolon.consumer.model.Student;
import com.vieira.sogolon.consumer.repository.StudentRepository;
import com.vieira.sogolon.consumer.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class ConsumerStore {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    private final Logger logger = LoggerFactory.getLogger(ConsumerStore.class);

    @KafkaListener(topics = "${topic.store}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String studentToSave) throws JsonProcessingException {
        logger.info(String.format("Student to be saved: {%s}", studentToSave));

        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(studentToSave, Student.class);

        LocalDateTime now = LocalDateTime.now();
        student.setCreated(now);

        String email;
        email = student.getEmail();

        boolean stop = searchStudent(studentRepository, mongoTemplate, email, student);

        if (stop) {
            System.out.println(student + " already exists");
        } else {
            studentService.save(student);
            logger.info(String.format("Student saved in database successfully: {%s}", student));
        }

    }

    private boolean searchStudent (StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        List<Student> students = mongoTemplate.find(query, Student.class);

        if (students.size() > 1) {
            throw new IllegalStateException("Found many students with email " + email);
        }

        if (students.isEmpty()) {
            System.out.println("Inserting student " + student);
            repository.insert(student);
            return false;
        } else {
            System.out.println(student + " already exists");
        }

        return true;
    }

}
