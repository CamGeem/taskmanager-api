package com.camille.taskmanager_api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskManager manager;

    public TaskController() {
        this.manager = new TaskManager();
        manager.loadTasks("tasks.txt");
    }

    // GET /tasks → list all tasks
    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return manager.getTasks();
    }

    // POST /tasks → add a new task
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        // Reuse TaskManager Logic
        Task newTask = manager.addTask(task.getName(), task.getDescription());
        manager.saveTasks("tasks.txt");
        return newTask;
    }

    // PUT /tasks/{id} → update a task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
        boolean updated = manager.editTask(id, task.getName(), task.getDescription());
        if (updated){
            manager.saveTasks("tasks.txt");
            return manager.getTasks().stream()
                    .filter(t -> t.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

    // DELETE /tasks/ {id} -> remove task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        boolean removed = manager.deleteTask(id);

        if (removed){
            manager.saveTasks("tasks.txt");
            return "Task removed successfully";
        }else {
            return "Task not found";
        }
    }
}