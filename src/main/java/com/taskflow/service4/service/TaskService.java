package com.taskflow.service4.service;

import com.taskflow.service4.dto.TaskDTO;
import com.taskflow.service4.dto.UserDetailsDTO;
import com.taskflow.service4.model.Priority;
import com.taskflow.service4.model.Task;
import com.taskflow.service4.repository.TaskRepository;
import org.apache.catalina.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    String userServiceURL = "http://localhost:8080/api/user/get_user_details/";

    private final TaskRepository taskRepository;
    private final RestTemplate restTemplate;
    public TaskService(TaskRepository taskRepository, RestTemplate restTemplate) {
        this.taskRepository = taskRepository;
        this.restTemplate = restTemplate;
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

    @Async
    public ResponseEntity<Task> updateTask(TaskDTO taskDTO){
        Task task = taskRepository.findById(taskDTO.getTaskId()).get();
        if(taskRepository.existsById(taskDTO.getTaskId())){
            task.setContent(taskDTO.getContent());
            task.setStatus(taskDTO.getStatus());
            taskRepository.save(task);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);

    }

    /*
    @Async
    public ResponseEntity<List<TaskDTO>> getUserTasks(Integer userId){
        List<Task> userTasks = taskRepository.findAllByAssignedToUserId(userId);
        String url = userServiceURL + userId;
        ResponseEntity<UserDetailsDTO> data = restTemplate
                .exchange(
                        userServiceURL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<UserDetailsDTO>() {
                        }
                );
        UserDetailsDTO userDetailsDTO = data.getBody();
        List<TaskDTO> taskDTOList = userTasks
                .stream()
                .map(task -> new TaskDTO(
                        task.getTaskId(),
                        task.getContent(),
                        task.getStatus(),
                        task.getPriority(),
                        task.getCreatedByUserId(),
                        userDetailsDTO,
                        task.getCreatedIn(),
                        task.getDependsOnTaskId()
                )).collect(Collectors.toList());
        return new ResponseEntity<>(taskDTOList,HttpStatus.OK);
    }*/

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

    @Async
    public ResponseEntity<Object> assignTask(Integer taskId, Integer userId){
        Task task = taskRepository.findById(taskId).get();
        task.setAssignedToUserId(userId);
        taskRepository.save(task);
        return new ResponseEntity<>("Task assigned successfully", HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteTask(Integer taskId){
        taskRepository.deleteById(taskId);
        return new ResponseEntity<>("TASK IS DELETED",HttpStatus.OK);
    }

    public Boolean checkSprintCompletion(List<Integer> tasksIds){
        List<Task> tasks = tasksIds
                .stream()
            .map(id-> taskRepository.findById(id).get())
                .collect(Collectors.toList());
        int tasksLength = tasks.size();
        int completedTasks = 0;
        for (Task task : tasks){
            if(task.getStatus().equals("COMPLETED")){
                completedTasks += 1;
            }
        }
        return completedTasks == tasksLength;
    }
}
