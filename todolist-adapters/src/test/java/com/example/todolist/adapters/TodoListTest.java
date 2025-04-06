package com.example.todolist.adapters;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class TodoListTest {

    @Inject
    private EntityManager em;

    @Test
    @Transactional
    public void check_large_text(){
        Sample s = new Sample();
        s.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."+
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        s.setSummary("This is a short summary");
        em.persist(s);
        assertNotNull(s.getId());
        System.out.println("Sample persisted with id: " + s.getId());
        long length = em.createQuery("select length(s.description) from Sample s where s.id = :id", Integer.class)
                .setParameter("id", s.getId())
                .getSingleResult()
                .longValue();
        assertTrue(length>255);

    }
}
