package com.vieira.sogolon.consumer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vieira.sogolon.consumer.model.Student;
import com.vieira.sogolon.consumer.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ConsumerStore {

    @Autowired
    private StudentService studentService;

    private final Logger logger = LoggerFactory.getLogger(ConsumerStore.class);

    @KafkaListener(topics = "${topic.store}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String studentToSave) throws JsonProcessingException {
        logger.info(String.format("Student to be saved: {%s}", studentToSave));

        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(studentToSave, Student.class);

        studentService.save(student);
        logger.info("Student saved in database successfully: {%s}", student);
    }

}
