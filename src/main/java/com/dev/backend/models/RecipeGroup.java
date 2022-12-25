package com.dev.backend.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "recipes_groups")
public class RecipeGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int sequence;

    @Column(nullable = false)
    private Type type;

    public enum Type {
        INGREDIENT("ingredient"),
        STEP("step");

        private String name;

        Type(String name) {
            this.name = name;
        }
    }
}
