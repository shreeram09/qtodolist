package com.example.todolist.adapters.config;

import com.example.todolist.adapters.common.CommonMetadata;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(2500)
public class CommonFilter implements ContainerRequestFilter {

    @Inject
    private CommonMetadata commonMetadata;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        this.commonMetadata.setSemId(requestContext.getHeaderString("simId"));
        this.commonMetadata.setBranchId(requestContext.getHeaderString("branchId"));
    }
}
