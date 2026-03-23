package com.camille;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskManager {
    ArrayList<Task>  tasks;
    private int nextId = 1;

    // Constructor
    public TaskManager(){
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    // Add a new task or update
    public Task addTask(String name, String description){
        Task task = new Task(nextId, name, description);
        if(name == null || name.isEmpty()){
            return null;
        }

        if(description.length() > 100){
            description = description.substring(0, 100);
        }

        tasks.add(task);
        System.out.println("Task added: " + task);
        nextId++;

        return task;
    }

    public boolean markTaskCompleted(int taskId) {
        for (Task task : tasks){
            if (task.getId() == taskId){
                task.markAsCompleted();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Task> getCompletedTasks(){
        ArrayList<Task> completed = new ArrayList<>();

        for (Task task : tasks){
            if (task.isCompleted()){
                completed.add(task);
            }
        }

        return completed;
    }

    public ArrayList<Task> getPendingTasks(){
        ArrayList<Task> pending = new ArrayList<>();
        for (Task task : tasks){
            if (!task.isCompleted()){
                pending.add(task);
            }
        }

        return pending;
    }


    public boolean deleteTask(int taskId){
        return tasks.removeIf(task -> task.getId() == taskId);
    }

    public boolean editTask(int taskId, String newName, String newDescription){
        for (Task task : tasks){
            if (task.getId() == taskId){
                //Update name of the task only if the new value is provided
                if(newName != null && !newName.trim().isEmpty()){
                    task.setName(newName);
                }

                if(newDescription != null && !newDescription.trim().isEmpty()){
                    task.setDescription(newDescription);
                }

                return true;
            }
        }
        return false;
    }

    public void loadTasks(String filename){
        try (Scanner scanner = new Scanner(new File(filename))){
            tasks.clear();
            int maxId = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] parts =  line.split("\\|");

                if (parts.length == 4){
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String description = parts[2];
                    boolean isCompleted = Boolean.parseBoolean(parts[3]);

                    Task task = new Task(id, name, description);

                    if(isCompleted){
                        task.markAsCompleted();
                    }

                    tasks.add(task);

                    if(id > maxId){
                        maxId = id;
                    }

                }
            }

            nextId = maxId + 1;
            System.out.println("Task loaded!");
        }catch (Exception e){
            System.out.println("Error loading tasks!" + e.getMessage());
        }
    }

    public void saveTasks(String filename){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filename))){
            for (Task task : tasks) {
                writer.println(task.getId() + "|" +
                        task.getName() + "|" +
                        task.getDescription() + "|" +
                        task.isCompleted());
            }
            System.out.println("Tasks saved to " + filename);
        }catch(IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}