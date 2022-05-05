package com.vieira.sogolon.consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vieira.sogolon.consumer.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;
}
