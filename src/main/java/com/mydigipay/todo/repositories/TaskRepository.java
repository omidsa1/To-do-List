package com.mydigipay.todo.repositories;

import com.mydigipay.todo.models.TaskDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<TaskDocument, String> {
}

