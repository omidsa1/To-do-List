package com.mydigipay.todo.controllers;

import com.mydigipay.todo.mappers.UserMapper;
import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.models.UserDto;
import com.mydigipay.todo.services.UserService;
import com.mydigipay.todo.services.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    UserDto signUp(@RequestBody UserDto user) {
        UserDocument userDocument = userService.save(userMapper.dtoToDocument(user));
        return userMapper.documentToDto(userDocument);
    }

    @PutMapping
    UserDto update(@RequestBody UserDto user) {
        UserDocument userDocument = userService.save(userMapper.dtoToDocument(user));
        return userMapper.documentToDto(userDocument);
    }

    @DeleteMapping
    void delete(@RequestBody UserDto user) {
        userService.delete(userMapper.dtoToDocument(user));
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable String id) {
        userService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    UserDto findById(@PathVariable String id) {
        return userMapper.documentToDto(userService.findById(id));
    }

    @GetMapping("/username/{username}")
    UserDto findByUsername(@PathVariable String username) {
        return userMapper.documentToDto(userService.findByUsername(username));
    }

    @GetMapping
    List<UserDto> findAll() {
        return userService.find()
                .stream()
                .map(document -> userMapper.documentToDto(document))
                .collect(Collectors.toList());
    }

    @PostMapping("/sign-in")
    void signIn(@RequestBody UserDocument userDocument) {
        userService.signIn(userDocument);
    }

    @PutMapping("/change-password") //dont need to this update method will do it
    void changePassword(@RequestParam String username, @RequestParam String newPassword) {
        userService.changePassword(username, newPassword);
    }
}
