package com.taskflow.service4.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.taskflow.service4.dto.TaskAssignmentDTO;
import com.taskflow.service4.dto.TaskDTO;
import com.taskflow.service4.model.Task;
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

    @PostMapping("/update_task")
    public ResponseEntity<Task> updateTask(@RequestBody TaskDTO taskDTO) throws JsonProcessingException {
        return taskService.updateTask(taskDTO);
    }

    @GetMapping("/get_project_tasks/{projectId}")
    public ResponseEntity<List<TaskDTO>> getProjectTasks(@PathVariable Integer projectId){
        return taskService.getProjectTasks(projectId);
    }

    @GetMapping("/get_user_tasks/{userId}")
    public ResponseEntity<List<TaskDTO>> getUserTasks(@PathVariable Integer userId){
        return taskService.getUserTasks(userId);
    }

    @PutMapping("/assign_task")
    public ResponseEntity<Object> assignTaskToUser(@RequestBody TaskAssignmentDTO taskAssignmentDTO){
        return taskService.assignTask(taskAssignmentDTO.getTaskId(),taskAssignmentDTO.getUserId());
    }
    /*
    @GetMapping("/get_tasks_by_creator/{userId}")
    public ResponseEntity<List<TaskDTO>> getTasksByCreator(@PathVariable Integer userId){
        return taskService.getTasksByCreator(userId);
    }*/
}
