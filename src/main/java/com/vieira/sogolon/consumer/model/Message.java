package com.vieira.sogolon.consumer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Message {

    @Id
    private String id;
    private String message;
    private String author;
}
