package com.mydigipay.todo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class TaskResponseDto {

    private String id;
    private String title;
    private String description;
    private String status;

    private UserResponseDto owner;
    private UserResponseDto assignee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserResponseDto getOwner() {
        return owner;
    }

    public void setOwner(UserResponseDto owner) {
        this.owner = owner;
    }

    public UserResponseDto getAssignee() {
        return assignee;
    }

    public void setAssignee(UserResponseDto assignee) {
        this.assignee = assignee;
    }
}
