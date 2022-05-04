package com.vieira.sogolon.consumer.service;

import com.vieira.sogolon.consumer.model.Message;
import com.vieira.sogolon.consumer.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message save(Message message) {
        messageRepository.save(message);
        return message;
    }

    public List<Message> getListMessage() {
        return messageRepository.findAll();
    }

}
