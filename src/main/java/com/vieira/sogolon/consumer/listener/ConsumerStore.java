package com.vieira.sogolon.consumer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vieira.sogolon.consumer.model.Message;
import com.vieira.sogolon.consumer.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
public class ConsumerStore {

    @Autowired
    private MessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(ConsumerStore.class);

    @KafkaListener(topics = "${topic.store}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String consumedMessage) throws JsonProcessingException {
        logger.info(String.format("Consumed message: {%s}", consumedMessage));

        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(consumedMessage, Message.class);

        messageService.save(message);
        logger.info("Message saved in database successfully: {%s}", message);
    }

}
