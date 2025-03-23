package com.example.todolist.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    String code;

    @Lob
    String description;
}
