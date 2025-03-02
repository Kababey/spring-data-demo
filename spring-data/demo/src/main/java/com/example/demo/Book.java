package com.example.demo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private Long age;
    private LocalDateTime publishDate;
}
