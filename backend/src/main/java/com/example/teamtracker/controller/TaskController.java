package com.example.teamtracker.controller;

import com.example.teamtracker.model.Task;
import com.example.teamtracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // ✅ Create a task
    @PostMapping("/create")
    public Task createTask(@RequestBody Task task,
                           @RequestParam Long projectId,
                           @RequestParam(required = false) Long assignedUserId) {
        return taskService.createTask(task, projectId, assignedUserId);
    }

    // ✅ Get all tasks by project
    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProject(projectId);
    }

    // ✅ Get a specific task
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // ✅ Update a task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    // ✅ Delete a task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully.";
    }

    // ✅ Reorder tasks
    @PostMapping("/reorder/{projectId}")
    public String reorderTasks(@PathVariable Long projectId, @RequestBody List<Long> orderedTaskIds) {
        taskService.reorderTasks(projectId, orderedTaskIds);
        return "Tasks reordered successfully.";
    }
}
