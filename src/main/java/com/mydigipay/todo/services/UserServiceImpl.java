package com.mydigipay.todo.services;

import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void signIn(UserDocument userDocument) {
    }

    @Override
    public void changePassword(String username, String newPassword) {
    }//Don't need,update is enough


    @Override
    public UserDocument save(UserDocument userDocument) {
        return userRepository.save(userDocument);
    }

    @Override
    public List<UserDocument> find() {
        return userRepository.findAll();
    }

    @Override
    public UserDocument findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public UserDocument findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public void delete(UserDocument userDocument) {
        userRepository.delete(userDocument);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }


}
