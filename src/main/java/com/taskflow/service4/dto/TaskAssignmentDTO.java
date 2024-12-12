package com.taskflow.service4.dto;

import lombok.Data;

@Data
public class TaskAssignmentDTO {

    Integer taskId;
    Integer userId;

    public TaskAssignmentDTO(
            Integer taskId,
            Integer userId
    ){
        this.taskId = taskId;
        this.userId = userId;
    }
}
