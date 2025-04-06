package com.example.todolist.adapters.out.persistence.mapper;

import com.example.todolist.adapters.out.persistence.entity.ToDo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TodoMapper {
    ToDo toEntity(com.example.todolist.domain.ToDo domain);
    com.example.todolist.domain.ToDo toDomain(ToDo entity);
    List<com.example.todolist.domain.ToDo> toDomainList(List<ToDo> entityList);
}
