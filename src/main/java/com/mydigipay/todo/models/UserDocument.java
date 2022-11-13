package com.mydigipay.todo.models;

import org.springframework.data.mongodb.core.mapping.Document;

// spring data mongo document class with id and name fields
@Document(collection = "users")
public class UserDocument {

    private String id;
    private String name;

    public UserDocument() {
    }

    public UserDocument(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
