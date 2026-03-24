package com.camille.taskmanager_api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TaskService {
    private final TaskManager manager;

    public TaskService(@Qualifier("inMemoryTaskManager")TaskManager manager) {
        this.manager = manager;
        manager.loadTasks("tasks.txt");
    }

    public List<Task> getTasks() {
        return manager.getTasks();
    }

    public Task addTask(String name, String description) {
        // Reuse TaskManager Logic
        Task newTask = manager.addTask(name, description);
        manager.saveTasks("tasks.txt");
        return newTask;
    }

    public Task updateTask(int id, String name, String description) {
        boolean updated = manager.editTask(id, name, description);
        if (updated){
            manager.saveTasks("tasks.txt");
            return manager.getTasks().stream()
                    .filter(t -> t.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }


    public boolean deleteTask(int id) {
        boolean removed = manager.deleteTask(id);

        if (removed){
            manager.saveTasks("tasks.txt");
        }

        return removed;
    }
}
