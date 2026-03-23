// Model
package com.camille.taskmanager_api;

public class Task {
    private int id;
    private String name;
    private String description;
    private boolean isCompleted;


    //Non-Parameterized Constructor
    public Task() {}


    //Parameterized Constructor
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    //setters
    private void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setCompleted(boolean isCompleted){
        this.isCompleted = true;
    }



    //getters
    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean isCompleted(){
        return this.isCompleted;
    }


    public void markAsCompleted() { this.isCompleted = true; }


    @Override
    public String toString() {
        String displayName = (name == null || name.isEmpty()) ? "N/A" : name;
        String displayDescription = (description == null || description.isEmpty()) ? "N/A" : description;

        return "[" + id + "] " + displayName + " - " + displayDescription + " - " + (isCompleted ? "Done" : "Pending");
    }
}
