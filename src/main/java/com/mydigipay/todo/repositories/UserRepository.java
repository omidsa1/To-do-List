package com.mydigipay.todo.repositories;

import com.mydigipay.todo.models.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {
}

