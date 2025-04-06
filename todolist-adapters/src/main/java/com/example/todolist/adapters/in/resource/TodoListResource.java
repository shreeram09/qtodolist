package com.example.todolist.adapters.in.resource;

import com.example.todolist.model.ToDoDto;
import com.example.todolist.adapters.common.CommonMetadata;
import com.example.todolist.api.TodoListApi;
import com.example.todolist.adapters.in.resource.mapper.TodoMapper;
import com.example.todolist.ports.out.TodoListPort;
import com.example.todolist.model.LogLevelResponse;
import com.example.todolist.model.LogLevelRequest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.Enumeration;

import org.jboss.logmanager.LogManager;
import org.jboss.logmanager.Logger;

@ApplicationScoped
public class TodoListResource implements TodoListApi {

    @Inject
    private TodoListPort port;

    @Inject
    private TodoMapper mapper;

    @Inject
    private CommonMetadata commonMetadata;

    @Transactional
    @Override
    public void create(ToDoDto item) {
        var domain = mapper.toDomain(item);
        port.add(domain);
    }

    @Transactional
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

    @Transactional
    @Override
    public void delete(String code) {
        if(code==null || code.isEmpty()) throw new BadRequestException();
        var found = port.retrieve().parallelStream().filter(item->item.getCode().equals(code)).findFirst().orElseThrow(NotFoundException::new);
        port.delete(found.getCode());
    }

    @Transactional
    @Override
    public List<ToDoDto> retrieve(String simId, String branchId) {
        System.out.println("simId = " + simId);
        System.out.println("branchId = " + branchId);
        return mapper.toModelList(port.retrieve());
    }


    @Override
    public Response changeLogLevel(LogLevelRequest request) {
        var newLevel = request.getLevel();
        if (newLevel == null || newLevel.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity("Log level must be provided").build();
        }

        String level = newLevel.toUpperCase();
        Level parsedLevel;

        try {
            parsedLevel = Level.parse(level);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid log level: " + level).build();
        }

        List<LogLevelResponse> updated = new ArrayList<>();
        var category = request.getCategory();
        if ("*".equals(category)) {
            Enumeration<String> loggerNames = LogManager.getLogManager().getLoggerNames();
            while (loggerNames.hasMoreElements()) {
                String name = loggerNames.nextElement();
                Logger logger = Logger.getLogger(name);
                if (logger != null) {
                    logger.setLevel(parsedLevel);
                    updated.add(new LogLevelResponse(name, parsedLevel.getName()));
                }
            }
        } else {
            Logger logger = Logger.getLogger(category);
            if (logger == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Logger category not found: " + category).build();
            }
            logger.setLevel(parsedLevel);
            updated.add(new LogLevelResponse(category, parsedLevel.getName()));
        }

        return Response.ok(updated).build();
    }

}
