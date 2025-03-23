package com.example.todolist.ports.out;

import com.example.todolist.domain.ToDo;

import java.util.List;

public interface TodoListPort {

    void add(ToDo todo);
    void delete(String code);
    List<ToDo> retrieve();
    void update(ToDo domain);
}
