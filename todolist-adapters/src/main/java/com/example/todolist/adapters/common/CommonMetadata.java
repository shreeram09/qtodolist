package com.example.todolist.adapters.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import lombok.Data;

@RequestScoped
@Data
public class CommonMetadata {

    String semId;
    String branchId;

    public CommonMetadata(){
        System.out.println("CommonMetadata created");
    }

    @PostConstruct
    public void init(){
        System.out.println("CommonMetadata initialized");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("CommonMetadata destroyed");
    }
}
