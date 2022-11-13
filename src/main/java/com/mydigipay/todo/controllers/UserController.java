package com.mydigipay.todo.controllers;

import com.mydigipay.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    void signUp(@RequestParam String username, @RequestParam String password) {
        userService.signUp(username, password);
    }
    @PostMapping("/sign-in")
    void signIn(@RequestParam String username, @RequestParam String password) {
        userService.signIn(username, password);
    }

    @PutMapping("/change-password")
    void changePassword(@RequestParam String username, @RequestParam String newPassword) {
        userService.changePassword(username, newPassword);
    }

    @DeleteMapping("/delete")
    void deleteUser(@RequestParam String username) {
        userService.deleteUser(username);
    }

    @GetMapping("/list-owned-tasks")
    void listOwnedTasks(@RequestParam String username) {
        userService.listOwnedTasks(username);
    }

    @GetMapping("/list-assigned-tasks")
    void listAssignedTasks(@RequestParam String username) {
        userService.listAssignedTasks(username);
    }
}
