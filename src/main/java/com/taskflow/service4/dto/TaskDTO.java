package com.taskflow.service4.dto;

import com.taskflow.service4.model.Task;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    Integer taskId;
    Integer projectId;
    String status;
    String content;
    String priority;
    Date createdIn;
    Integer createdByUserId;
    Integer assignedToUserId;
    Integer dependsOnTaskId;

    public TaskDTO(
            Integer taskId,
            String content,
            String status,
            String priority,
            Integer createdByUserId,
            Integer assignedToUserId,
            Date createdIn,
            Integer dependsOnTaskId
    ){
        this.assignedToUserId  = assignedToUserId;
        this.content = content;
        this.taskId = taskId;
        this.status = status;
        this.priority = priority;
        this.createdByUserId = createdByUserId;
        this.createdIn = createdIn;
        this.dependsOnTaskId = dependsOnTaskId;
    }
}
