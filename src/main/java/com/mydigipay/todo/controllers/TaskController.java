package com.mydigipay.todo.controllers;


import com.mydigipay.todo.models.TaskDTO;
import com.mydigipay.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    List<TaskDTO> listTasks(@RequestParam String type) {
        return taskService.getTasks(type);

    }

    @PutMapping
    TaskDTO updateTask(@RequestBody TaskDTO task) {
        return taskService.updateTask(task);
    }

    @PostMapping("/assign")
    TaskDTO assignTask(@RequestParam Integer taskId, @RequestParam Integer assigneeId) {
        return taskService.assignTask(taskId, assigneeId);
    }

    @DeleteMapping
    void deleteTask(@RequestParam Integer taskId) {
        taskService.deleteTask(taskId);
    }

}

