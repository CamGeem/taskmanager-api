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
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<TaskDTO> tasks = service.getTasks();
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
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int id) {
        TaskDTO task = service.getTaskById(id);

       return  ResponseEntity.ok(task);
    }

    // POST /tasks → add a new task
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task newTask = service.addTask(task.getName(), task.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
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
        service.markTaskCompleted(id);

        return ResponseEntity.ok("Task marked as completed");
    }

    // DELETE /tasks/ {id} -> remove task
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
       service.deleteTask(id);

        return ResponseEntity.ok("Task removed successfully");
    }
}