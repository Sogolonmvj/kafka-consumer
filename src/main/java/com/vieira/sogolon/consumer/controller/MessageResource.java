package com.vieira.sogolon.consumer.controller;

import com.vieira.sogolon.consumer.model.Message;
import com.vieira.sogolon.consumer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageResource {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> saveMessage(@RequestBody Message message){
        messageService.save(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<List<Message>> getListMessage(){
        List<Message> messageList = messageService.getListMessage();
        return ResponseEntity.ok(messageList);
    }
}
