package com.mydigipay.todo.controllers;


import com.mydigipay.todo.mappers.TaskMapper;
import com.mydigipay.todo.models.TaskDocument;
import com.mydigipay.todo.models.TaskDto;
import com.mydigipay.todo.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {


    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public TaskDto save(@RequestBody TaskDto task, @RequestParam String userId){
        //todo userId param should be removed and gotten by security
        TaskDocument taskDocument = taskService.create(taskMapper.dtoToDocument(task), userId);
        return taskMapper.documentToDto(taskDocument);
    }

    @PutMapping
    TaskDto updateTask(@RequestBody TaskDto task) {
        TaskDocument taskDocument = taskService.save(taskMapper.dtoToDocument(task));
        return taskMapper.documentToDto(taskDocument);
    }

    @GetMapping
    List<TaskDto> findTasks(@RequestParam String type,@RequestParam String userId) {
        //todo userId param should be removed and gotten by security
        return taskService.getUsersTasks(type,userId)
                .stream()
                .map(document->taskMapper.documentToDto(document))
                .collect(Collectors.toList());
    }

    @PostMapping("/assign")
    TaskDto assignTask(@RequestParam String taskId, @RequestParam String assigneeId) {
        return taskMapper.documentToDto(taskService.assignTask(taskId, assigneeId));
    }

    @DeleteMapping("/{taskId}")
    void deleteTask(@PathVariable String taskId) {
        taskService.deleteTask(taskId);
    }

}

