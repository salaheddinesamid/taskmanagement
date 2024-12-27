package com.taskflow.service4.dto;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryDTO {

    private Date actionDate;
    private UserDetailsDTO userDTO;
    private String action;
    private Integer projectId;

    public HistoryDTO(
            Date actionDate,
            UserDetailsDTO userDTO,
            String action,
            Integer projectId
    ){
        this.actionDate = actionDate;
        this.userDTO = userDTO;
        this.action = action;
        this.projectId = projectId;
    }
}
