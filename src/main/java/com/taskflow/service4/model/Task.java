package com.taskflow.service4.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer taskId;

    @Column(name = "project_id")
    Integer projectId;

    @Column(name = "priority")
    String priority;

    @Column(name = "content")
    String content;

    @Column(name = "status")
    String status;

    @Column(name = "depends_on")
    Integer dependsOnTaskId;

    @Column(name = "assignedTo")
    Integer assignedToUserId;

    @Column(name = "createdIn")
    Date createdIn;

    @Column(name = "createdBy")
    Integer createdByUserId;


}
