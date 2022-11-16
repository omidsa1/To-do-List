package com.mydigipay.todo.services;

import com.mydigipay.todo.models.TaskDocument;
import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public TaskDocument save(TaskDocument taskDocument) {
        return taskRepository.save(taskDocument);
    }
    @Override
    public TaskDocument create(TaskDocument taskDocument, String userId) {
        //todo user not found exception should be converted
        UserDocument user = userService.findById(userId);
        taskDocument.setOwner(user);
        return save(taskDocument);
    }

    @Override
    public TaskDocument findById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tsk not found"));
    }

    @Override
    public List<TaskDocument> getUsersTasks(String type, String userId) {
        //todo user not found exception should be converted
        UserDocument user = userService.findById(userId);
        if(type.equalsIgnoreCase("assigned"))
            return taskRepository.findByAssignee(user);
        else if(type.equalsIgnoreCase("created"))
            return taskRepository.findByOwner(user);
        else
        return null;
    }


    @Override
    public TaskDocument assignTask(String taskId, String assigneeId) {
        //todo user not found exception should be converted
        //todo task not found exception should be converted
        TaskDocument task = findById(taskId);
        UserDocument user = userService.findById(assigneeId);
        task.setAssignee(user);
        return save(task);

    }
    @Override
    public TaskDocument unAssignTask(String taskId) {
        //todo task not found exception should be converted
        TaskDocument task = findById(taskId);
        task.setAssignee(null);
        return save(task);
    }

    @Override
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
