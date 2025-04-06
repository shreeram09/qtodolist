package com.example.todolist.entrypoint;

import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {

    public static void main(String... args) {
        io.quarkus.runtime.Quarkus.run(args);
    }
}
