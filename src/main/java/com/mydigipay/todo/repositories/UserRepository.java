package com.mydigipay.todo.repositories;

import com.mydigipay.todo.models.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDTO, String> {
}

