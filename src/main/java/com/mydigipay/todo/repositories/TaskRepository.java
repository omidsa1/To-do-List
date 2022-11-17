package com.mydigipay.todo.repositories;

import com.mydigipay.todo.models.TaskDocument;
import com.mydigipay.todo.models.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<TaskDocument, String> {
    List<TaskDocument> findByAssignee(UserDocument userDocument);
    List<TaskDocument> findByOwner(UserDocument userDocument);
}

