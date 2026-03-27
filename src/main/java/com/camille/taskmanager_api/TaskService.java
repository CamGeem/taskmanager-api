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

    public TaskService(@Qualifier("taskManagerImplement")    TaskManager manager) {
        this.manager = manager;
        manager.loadTasks("tasks.txt");
    }

    public List<Task> getTasks() {
        return manager.getTasks();
    }

    public Task addTask(String name, String description) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }

        if (description != null && description.length() > 100) {
            description = description.substring(0, 100);
        }

        Task newTask = manager.addTask(name, description);
        manager.saveTasks("tasks.txt");
        return newTask;
    }

    public List<Task> getCompletedTasks(){
        return manager.getCompletedTasks();
    }

    public List<Task> getPendingTasks(){
        return manager.getPendingTasks();
    }

    public Task getTaskById(int id) {
        return getTasks().stream().filter(task -> task.getId() == id).findFirst().orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }

    public void markTaskCompleted(int id){
        boolean updated = manager.markTaskCompleted(id);

        if(!updated){
            throw new IllegalArgumentException("Task not found with id: " + id);
        }

        manager.saveTasks("tasks.txt");
    }

    // Current Problem: Redundancy
    public Task updateTask(int id, String name, String description) {
        boolean updated = manager.editTask(id, name, description);


        if (!updated) {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }

        manager.saveTasks("tasks.txt");

       return manager.getTasks().stream().filter(task -> task.getId() == id).findFirst().orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }


    public void deleteTask(int id) {
        boolean removed = manager.deleteTask(id);

        if (!removed){
            throw new IllegalArgumentException("Task not found with id: " + id);
        }

        manager.saveTasks("tasks.txt");

    }
}
