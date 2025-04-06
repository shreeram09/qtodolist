package com.example.todolist.adapters.in.resource.mapper;

import com.example.todolist.model.ToDoDto;
import com.example.todolist.domain.ToDo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TodoMapper {

    ToDo toDomain(ToDoDto todo);
    List<ToDoDto> toModelList(List<ToDo> domainList);

    ToDoDto toModel(ToDo domain);
}
