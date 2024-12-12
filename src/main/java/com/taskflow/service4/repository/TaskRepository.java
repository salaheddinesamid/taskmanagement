package com.taskflow.service4.repository;

import com.taskflow.service4.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findAllByProjectId(Integer projectId);
}
