package com.example.models;

public class Player {
    private final String id;
    private final String description;

    public Player(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
