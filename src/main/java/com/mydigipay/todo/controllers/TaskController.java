package com.mydigipay.todo.controllers;


import com.mydigipay.todo.mappers.TaskMapper;
import com.mydigipay.todo.models.TaskDocument;
import com.mydigipay.todo.models.TaskDto;
import com.mydigipay.todo.models.TaskResponseDto;
import com.mydigipay.todo.services.TaskService;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public TaskResponseDto save(@RequestBody TaskDto task) {
        TaskDocument taskDocument = taskService.create(taskMapper.dtoToDocument(task));
        return taskMapper.documentToDto(taskDocument);
    }

    @PutMapping
    TaskResponseDto updateTask(@RequestBody TaskDto task) {
        TaskDocument taskDocument = taskService.update(taskMapper.dtoToDocument(task));
        return taskMapper.documentToDto(taskDocument);
    }

    @GetMapping
    List<TaskResponseDto> findTasks(@RequestParam String type) {

        return taskService.getUsersTasks(type)
                .stream()
                .map(document->taskMapper.documentToDto(document))
                .collect(Collectors.toList());
    }

    @PostMapping("/assign")
    TaskResponseDto assignTask(@RequestParam String taskId, @RequestParam String assigneeId) {
        return taskMapper.documentToDto(taskService.assignTask(taskId, assigneeId));
    }

    @PostMapping("/unAssign")
    TaskResponseDto assignTask(@RequestParam String taskId) {
        return taskMapper.documentToDto(taskService.unAssignTask(taskId));
    }

    @DeleteMapping("/{taskId}")
    void deleteTask(@PathVariable String taskId) {
        taskService.deleteTask(taskId);
    }

}

