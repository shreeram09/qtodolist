package com.example.todolist.adapters.out.persistence;

import com.example.todolist.adapters.out.persistence.entity.ToDo;
import com.example.todolist.adapters.out.persistence.mapper.TodoMapper;
import com.example.todolist.ports.out.TodoListPort;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TodoAdapter implements TodoListPort, PanacheRepository<ToDo> {

    @Inject
    private TodoMapper mapper;

    @Override
    public void add(com.example.todolist.domain.ToDo todo) {
        persistAndFlush(mapper.toEntity(todo));
    }

    @Override
    public void delete(String code) {
        var deleted = delete("from todo_list where code = :code", Parameters.with("code", code));
        System.out.println("Deleted: " + deleted);
    }

    @Override
    public List<com.example.todolist.domain.ToDo> retrieve() {
        return mapper.toDomainList(findAll().list());
    }

    @Override
    public void update(com.example.todolist.domain.ToDo domain) {
        update("name = :name, description = :description where code = :code",
                Parameters.with("name", domain.getName())
                        .and("description", domain.getDescription())
                        .and("code", domain.getCode()));
    }
}
