package com.camille.taskmanager_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // GET /tasks → list all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = service.getTasks();
        return ResponseEntity.ok(tasks);
    }

    // GET /tasks → list all completed tasks
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        return ResponseEntity.ok(service.getCompletedTasks());
    }

    // GET /tasks → list all pending tasks
    @GetMapping("/pending")
    public ResponseEntity<List<Task>> getPendingTasks() {
        return ResponseEntity.ok(service.getPendingTasks());
    }

    // Get /task by id
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Task task = service.getTaskById(id);

        if (task != null) {
            return ResponseEntity.ok(task);
        }else  {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /tasks → add a new task
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        try {
            Task newTask = service.addTask(task.getName(), task.getDescription());
            return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // PUT /tasks/{id} → update a task
    @PutMapping("/{id}")
    public ResponseEntity <Task> updateTask(@PathVariable int id, @RequestBody Task task) {
        Task updatedTask = service.updateTask(id, task.getName(), task.getDescription());
        if(updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<String> completeTask(@PathVariable int id) {
        boolean updated = service.markTaskCompleted(id);

        if (updated) {
            return ResponseEntity.ok("Task marked as completed");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }

    // DELETE /tasks/ {id} -> remove task
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        boolean removed = service.deleteTask(id);

        if (removed){
            return ResponseEntity.ok("Task removed successfully");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }
}