package com.example.todolist.api.config;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

@OpenAPIDefinition(
        info=@Info(
                title = "",
                version = ""
        ),
        components =@Components(
                responses = {
                        @APIResponse(responseCode = "401", description = "Invalid Authorization token"),
                        @APIResponse(responseCode = "403", description = "Not authorized to access this resource")
                }
        ),
        security = {@SecurityRequirement(name="bearerAuth")}
)
public class OpenAPIConfig extends Application {}
