package com.example.todolist.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "todo_seq")
    @SequenceGenerator(name = "todo_seq", sequenceName = "ToDo_SEQ", allocationSize = 1,initialValue = 1)
    Long id;

    String name;
    String code;

    @Lob
    String description;
}
