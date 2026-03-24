package com.camille.taskmanager_api;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public interface TaskManager {
    ArrayList<Task> getTasks();
    Task addTask(String name, String description);
    boolean markTaskCompleted(int taskId);
    ArrayList<Task> getCompletedTasks();
    ArrayList<Task> getPendingTasks();
    public boolean deleteTask(int taskId);
    public boolean editTask(int taskId, String newName, String newDescription);
    public void loadTasks(String filename);
    public void saveTasks(String filename);
}