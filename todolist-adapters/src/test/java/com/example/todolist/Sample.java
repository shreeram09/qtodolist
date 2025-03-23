package com.example.todolist;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sample_seq")
    @SequenceGenerator(name = "sample_seq", sequenceName = "SAMPLE_SEQ", allocationSize = 1,initialValue = 1)
    private Long id;
    @Lob
    private String description;
    private String summary;
}
