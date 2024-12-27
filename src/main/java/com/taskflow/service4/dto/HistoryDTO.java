package com.taskflow.service4.dto;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryDTO {

    private Date actionDate;
    private Integer userId;
    private String action;
    private Integer projectId;
    /*
    public HistoryDTO(
            Date actionDate,
            Integer userId,
            String action,
            Integer projectId
    ){
        this.actionDate = actionDate;
        this.userId = userId;
        this.action = action;
        this.projectId = projectId;
    }*/
}
