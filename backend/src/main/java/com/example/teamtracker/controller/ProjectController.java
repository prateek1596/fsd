package com.example.teamtracker.controller;

import com.example.teamtracker.model.Project;
import com.example.teamtracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects") // <-- plural and matches frontend
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // ✅ Create project (assign to team)
    @PostMapping("/create")
    public Project createProject(@RequestBody Project project, @RequestParam Long teamId) {
        return projectService.createProject(project, teamId);
    }

    // ✅ Get all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // ✅ Get project by ID
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    // ✅ Update project
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return projectService.updateProject(id, updatedProject);
    }

    // ✅ Delete project
    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "Project deleted successfully.";
    }
}
