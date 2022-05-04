package com.vieira.sogolon.consumer.repository;

import com.vieira.sogolon.consumer.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    public Message save(Message message);
}
