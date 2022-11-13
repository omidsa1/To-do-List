package com.mydigipay.todo.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class TaskDocument {

        @Id
        private String id;
        private String Title;
        private String Description;
        private String Status;

        @DBRef(db="users")
        private UserDocument Owner;

        @DBRef(db="users")
        private UserDocument Assignee;


        public TaskDocument() {
        }

        public TaskDocument(String id, String Title, String Description, String Status, UserDocument Owner, UserDocument Assignee) {
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

        public UserDocument getOwner() {
            return Owner;
        }

        public void setOwner(UserDocument Owner) {
            this.Owner = Owner;
        }

        public UserDocument getAssignee() {
            return Assignee;
        }

        public void setAssignee(UserDocument Assignee) {
            this.Assignee = Assignee;
        }

}
