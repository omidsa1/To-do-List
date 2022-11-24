package com.mydigipay.todo.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "tasks")
public class TaskDocument {

        @Id
        private String id;
        private String title;
        private String description;
        private String status;

        @DBRef
        private UserDocument owner;

        @DBRef
        private UserDocument assignee;


        public TaskDocument() {
        }

        public TaskDocument(String id, String title, String description, String status, UserDocument owner, UserDocument assignee) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.status = status;
            this.owner = owner;
            this.assignee = assignee;
        }

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

        public UserDocument getOwner() {
            return owner;
        }

        public void setOwner(UserDocument owner) {
            this.owner = owner;
        }

        public UserDocument getAssignee() {
            return assignee;
        }

        public void setAssignee(UserDocument assignee) {
            this.assignee = assignee;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDocument that = (TaskDocument) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(status, that.status) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(assignee, that.assignee);
    }

}
