package com.taskflow.service4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer bugId;

    @Column(name = "created_by")
    Integer createdByUserId;

    @Column(name = "description")
    String description;

    @Enumerated(EnumType.STRING)
    Priority priority;

    @Column(name = "taskId")
    Integer taskId;
}
