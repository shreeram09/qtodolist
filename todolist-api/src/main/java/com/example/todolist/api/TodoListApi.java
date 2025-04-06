package com.example.todolist.api;

import java.util.List;

import com.example.todolist.model.ToDoDto;
import com.example.todolist.model.LogLevelRequest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/todo")
public interface TodoListApi {

    @POST
    @Path("/list")
    @Operation(summary = "Create a new ToDo item")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "ToDo item created successfully"),
            @APIResponse(responseCode = "400", description = "Invalid input")
    })
    public void create(@RequestBody ToDoDto item);

    @Path("/list/{code}")
    @PUT
    @Operation(summary = "Update an existing ToDo item")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "ToDo item updated successfully"),
            @APIResponse(responseCode = "404", description = "ToDo item not found")
    })
    public ToDoDto update(@PathParam("code") String code, @RequestBody ToDoDto item);

    @DELETE
    @Path("/list/{code}")
    @Operation(summary = "Delete a ToDo item")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "ToDo item deleted successfully"),
            @APIResponse(responseCode = "404", description = "ToDo item not found")
    })
    public void delete(@PathParam("code") String code);

    @GET
    @Path("/list")
    @Operation(summary = "Retrieve all ToDo items")
    @Parameters({
            @Parameter(name = "sim-id", description = "The sim-id of the user", required = true),
            @Parameter(name = "branch-id", description = "The branch-id of the user", required = true),
    })
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "List of ToDo items retrieved successfully")
    })
    public List<ToDoDto> retrieve(@HeaderParam("sim-id") String simId, @HeaderParam("branch-id") String branchId);

    //put endpoint to change log level for given category/categories and return list of category/categories with respective new log level
    @PUT
    @Path("/loglevel")
    @Operation(summary = "Change log level for given category/categories")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Log level changed successfully"),
            @APIResponse(responseCode = "400", description = "Invalid input")
    })
    public Response changeLogLevel(@RequestBody LogLevelRequest request);

}
