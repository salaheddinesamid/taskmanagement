package com.taskflow.service4.controller;

import com.taskflow.service4.dto.TaskDTO;
import com.taskflow.service4.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/new_task")
    public ResponseEntity<Object> createTask(@RequestBody TaskDTO taskDTO){
        return taskService.createNewTask(taskDTO);
    }

    @GetMapping("/get_project_tasks/{projectId}")
    public ResponseEntity<List<TaskDTO>> getProjectTasks(@PathVariable Integer projectId){
        return taskService.getProjectTasks(projectId);
    }
}
