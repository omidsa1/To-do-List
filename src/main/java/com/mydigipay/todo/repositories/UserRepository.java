package com.mydigipay.todo.repositories;

import com.mydigipay.todo.models.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByName(String name);
}

