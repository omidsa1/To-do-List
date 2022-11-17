package com.mydigipay.todo.services;

import com.mydigipay.todo.models.UserDocument;

import java.util.List;

public interface UserService {
    void signIn(UserDocument userDocument);

    void changePassword(String username, String newPassword);//Don't need,update is enough;

    UserDocument save(UserDocument userDocument);

    List<UserDocument> find();

    UserDocument findById(String id);

    UserDocument findByUsername(String username);

    void delete(UserDocument userDocument);

    void deleteById(String id);
}
