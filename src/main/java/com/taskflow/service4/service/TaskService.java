package com.taskflow.service4.service;

import com.taskflow.service4.dto.TaskDTO;
import com.taskflow.service4.model.Priority;
import com.taskflow.service4.model.Task;
import com.taskflow.service4.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    // Create New task:

    public ResponseEntity<Object> createNewTask(TaskDTO taskDTO){
        Task newTask = new Task();
        newTask.setProjectId(taskDTO.getProjectId());
        newTask.setContent(taskDTO.getContent());
        newTask.setCreatedIn(taskDTO.getCreatedIn());
        newTask.setDependsOnTaskId(taskDTO.getDependsOnTaskId());
        newTask.setPriority(taskDTO.getPriority());
        newTask.setStatus(taskDTO.getStatus());
        taskRepository.save(newTask);

        return new ResponseEntity<>("TASK CREATED",HttpStatus.OK);

    }

    public void updateTask(){

    }

    public ResponseEntity<List<TaskDTO>> getProjectTasks(Integer projectId){
       List<Task> tasks = taskRepository.findAllByProjectId(projectId);
       List<TaskDTO> taskDTOList = tasks
               .stream()
               .map(task -> new TaskDTO(
                       task.getTaskId(),
                       task.getContent(),
                       task.getStatus(),
                       task.getPriority(),
                       task.getCreatedByUserId(),
                       task.getAssignedToUserId(),
                       task.getCreatedIn(),
                       task.getProjectId()
               )).collect(Collectors.toList());

       return new ResponseEntity<>(taskDTOList,HttpStatus.OK);

    }

    public void getMemberTask(){

    }

    public void assignTask(Integer taskId, Integer userId){

    }

    public ResponseEntity<Object> deleteTask(Integer taskId){
        taskRepository.deleteById(taskId);
        return new ResponseEntity<>("TASK IS DELETED",HttpStatus.OK);
    }
}
