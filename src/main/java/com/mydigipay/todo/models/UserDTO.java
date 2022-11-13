package com.mydigipay.todo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class UserDTO {

    private String id;
    private String Name;

    public UserDTO() {
    }

    public UserDTO(String id, String Name) {
        this.id = id;
        this.Name = Name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

}
