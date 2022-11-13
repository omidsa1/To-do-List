package com.mydigipay.todo.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class TaskDTO {

    @Id
    private String id;
    private String Title;
    private String Description;
    private String Status;

    @DBRef(db="users")
    private UserDTO Owner;

    @DBRef(db="users")
    private UserDTO Assignee;


    public TaskDTO() {
    }

    public TaskDTO(String id, String Title, String Description, String Status, UserDTO Owner, UserDTO Assignee) {
        this.id = id;
        this.Title = Title;
        this.Description = Description;
        this.Status = Status;
        this.Owner = Owner;
        this.Assignee = Assignee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public UserDTO getOwner() {
        return Owner;
    }

    public void setOwner(UserDTO Owner) {
        this.Owner = Owner;
    }

    public UserDTO getAssignee() {
        return Assignee;
    }

    public void setAssignee(UserDTO Assignee) {
        this.Assignee = Assignee;
    }

}
