package com.taskflow.service4.dto;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Data
public class UserDTO {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public UserDTO(
            Integer userId,
            String firstName,
            String lastName,
            String email,
            String role
    ){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
