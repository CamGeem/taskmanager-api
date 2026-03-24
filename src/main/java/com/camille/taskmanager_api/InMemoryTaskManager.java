package com.camille.taskmanager_api;

import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class InMemoryTaskManager implements TaskManager {

    private final ArrayList<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    @Override
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    public Task addTask(String name, String description) {
        Task task = new Task(nextId++, name, description);
        tasks.add(task);
        return task;
    }

    @Override
    public boolean deleteTask(int taskId) {
        return tasks.removeIf(task -> task.getId() == taskId);
    }

    @Override
    public boolean editTask(int taskId, String newName, String newDescription) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setName(newName);
                task.setDescription(newDescription);
                return true;
            }
        }
        return false;
    }

    @Override
    public void loadTasks(String filename) {}

    @Override
    public void saveTasks(String filename) {}

    @Override
    public boolean markTaskCompleted(int taskId) { return false; }

    @Override
    public ArrayList<Task> getCompletedTasks() { return new ArrayList<>(); }

    @Override
    public ArrayList<Task> getPendingTasks() { return new ArrayList<>(); }
}