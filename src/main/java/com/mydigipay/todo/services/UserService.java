package com.mydigipay.todo.services;

import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.repositories.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void signIn(UserDocument userDocument) {
    }

    public void changePassword(String username, String newPassword) {
    }//Don't need,update is enough


    public UserDocument save(UserDocument userDocument) {
        return userRepository.save(userDocument);
    }

    public List<UserDocument> find(){
        return userRepository.findAll();
    }

    public UserDocument findById(String id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
    }

    public UserDocument findByUsername(String username) {
        return userRepository.findByName(username).orElseThrow(()->new RuntimeException("user not found"));
    }
    public void delete(UserDocument userDocument){
        userRepository.delete(userDocument);
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }


}
