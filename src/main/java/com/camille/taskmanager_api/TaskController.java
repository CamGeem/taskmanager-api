package com.camille.taskmanager_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TaskController {
    private final TaskManager manager;

    public TaskController() {
        this.manager = new TaskManager();
        manager.loadTasks("tasks.txt");
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return manager.getTasks();
    }
}