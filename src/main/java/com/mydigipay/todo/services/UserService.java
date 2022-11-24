package com.mydigipay.todo.services;

import com.mydigipay.todo.models.UserDocument;

import java.util.List;

public interface UserService {
    void signIn(UserDocument userDocument);

    UserDocument save(UserDocument userDocument);

    List<UserDocument> find();

    UserDocument findById(String id);

    UserDocument findByUsername(String username);

    void deleteById(String id);

    UserDocument update(UserDocument userDocument);
}
