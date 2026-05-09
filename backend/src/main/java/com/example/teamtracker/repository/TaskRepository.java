package com.example.teamtracker.repository;

import com.example.teamtracker.model.Task;
import com.example.teamtracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectOrderByOrderIndexAsc(Project project);
}
