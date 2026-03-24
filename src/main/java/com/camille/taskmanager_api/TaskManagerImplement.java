package com.camille.taskmanager_api;

import java.util.ArrayList;

public class TaskManagerImplement implements TaskManager {
    @Override
    public ArrayList<Task> getTasks() {
        return null;
    }

    @Override
    public Task addTask(String name, String description) {
        return null;
    }

    @Override
    public boolean markTaskCompleted(int taskId) {
        return false;
    }

    @Override
    public ArrayList<Task> getCompletedTasks() {
        return null;
    }

    @Override
    public ArrayList<Task> getPendingTasks() {
        return null;
    }

    @Override
    public boolean deleteTask(int taskId) {
        return false;
    }

    @Override
    public boolean editTask(int taskId, String newName, String newDescription) {
        return false;
    }

    @Override
    public void loadTasks(String filename) {

    }

    @Override
    public void saveTasks(String filename) {

    }
}
