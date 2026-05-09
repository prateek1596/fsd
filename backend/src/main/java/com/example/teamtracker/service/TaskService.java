package com.example.teamtracker.service;

import com.example.teamtracker.model.AppUser;
import com.example.teamtracker.model.Project;
import com.example.teamtracker.model.Task;
import com.example.teamtracker.repository.AppUserRepository;
import com.example.teamtracker.repository.ProjectRepository;
import com.example.teamtracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    // ✅ Create a new task
    public Task createTask(Task task, Long projectId, Long assignedUserId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        task.setProject(project);
        task.setOrderIndex(project.getId().intValue()); // Default order

        if (assignedUserId != null) {
            AppUser user = appUserRepository.findById(assignedUserId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            task.setAssignedUser(user);
        }

        return taskRepository.save(task);
    }

    // ✅ Get all tasks for a project
    public List<Task> getTasksByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return taskRepository.findByProjectOrderByOrderIndexAsc(project);
    }

    // ✅ Get one task
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // ✅ Update task
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());

        return taskRepository.save(existingTask);
    }

    // ✅ Delete task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // ✅ Reorder tasks (drag-and-drop style)
    public void reorderTasks(Long projectId, List<Long> orderedTaskIds) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<Task> tasks = taskRepository.findByProjectOrderByOrderIndexAsc(project);

        for (int i = 0; i < orderedTaskIds.size(); i++) {
            Long taskId = orderedTaskIds.get(i);
            for (Task t : tasks) {
                if (t.getId().equals(taskId)) {
                    t.setOrderIndex(i);
                }
            }
        }
        taskRepository.saveAll(tasks);
    }
}
