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

    // POST /tasks → add a new task
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task newTask = service.addTask(task.getName(), task.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    // PUT /tasks/{id} → update a task
    @PutMapping("/{id}")
    public ResponseEntity <Task> updateTask(@PathVariable int id, @RequestBody Task task) {
        Task updateTask = service.updateTask(id, task.getName(), task.getDescription());
        if(updateTask != null) {
            return ResponseEntity.ok(updateTask);
        }else {
            return ResponseEntity.notFound().build();
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