package com.camille.taskmanager_api;

public class TaskDTO {
    private int id;
    private String name;
    private boolean completed;

    public TaskDTO(int id, String name, boolean completed) {
        this.id = id;
        this.name = name;
        this.completed = completed;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }
}
