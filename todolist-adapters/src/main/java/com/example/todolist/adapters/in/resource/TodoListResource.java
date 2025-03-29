package com.example.todolist.adapters.in.resource;

import com.example.todolist.ToDoDto;
import com.example.todolist.adapters.common.CommonMetadata;
import com.example.todolist.TodoListApi;
import com.example.todolist.adapters.in.resource.mapper.TodoMapper;
import com.example.todolist.ports.out.TodoListPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;

@ApplicationScoped
public class TodoListResource implements TodoListApi {

    @Inject
    private TodoListPort port;

    @Inject
    private TodoMapper mapper;

    @Inject
    private CommonMetadata commonMetadata;

    @Override
    public void create(ToDoDto item) {
        var domain = mapper.toDomain(item);
        port.add(domain);
    }

    @Override
    public ToDoDto update(String code, ToDoDto item) {
        var existingTodo = port.retrieve().stream()
                .filter(todo -> todo.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("ToDo item not found"));

        existingTodo.setName(item.getName());
        existingTodo.setDescription(item.getDescription());
        port.update(existingTodo);

        return mapper.toModel(existingTodo);
    }

    @Override
    public void delete(String code) {
        if(code==null || code.isEmpty()) throw new BadRequestException();
        var found = port.retrieve().parallelStream().filter(item->item.getCode().equals(code)).findFirst().orElseThrow(NotFoundException::new);
        port.delete(found.getCode());
    }

    @Override
    public List<ToDoDto> retrieve(String simId, String branchId) {
        System.out.println("simId = " + simId);
        System.out.println("branchId = " + branchId);
        return mapper.toModelList(port.retrieve());
    }
}
