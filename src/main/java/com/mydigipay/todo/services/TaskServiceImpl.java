package com.mydigipay.todo.services;

import com.mydigipay.todo.models.TaskDocument;
import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.repositories.TaskRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public TaskDocument update(TaskDocument taskDocument) {
        String username = getUsername();
        TaskDocument task = findById(taskDocument.getId());
        taskDocument.setOwner(task.getOwner());
        taskDocument.setAssignee(task.getAssignee());
        if(task.getAssignee() != null && task.getAssignee().getUsername().equals(username))
            return taskRepository.save(taskDocument);
        else if(task.getOwner() != null && task.getOwner().getUsername().equals(username))
            return taskRepository.save(taskDocument);
        else throw new RuntimeException("just owner or assignee can update the task");
    }
    @Override
    public TaskDocument create(TaskDocument taskDocument) {
        String username = getUsername();
        UserDocument user = userService.findByUsername(username);
        taskDocument.setOwner(user);
        return save(taskDocument);
    }

    private String getUsername() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public TaskDocument findById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("task not found"));
    }

    @Override
    public List<TaskDocument> getUsersTasks(String type) {

        String username = getUsername();
        UserDocument user = userService.findByUsername(username);
        if(type.equalsIgnoreCase("assigned"))
            return taskRepository.findByAssignee(user);
        else if(type.equalsIgnoreCase("created"))
            return taskRepository.findByOwner(user);
        else
            throw new RuntimeException("type parameter should be assigned or created");
    }


    @Override
    public TaskDocument assignTask(String taskId, String assigneeId) {

        TaskDocument task = findById(taskId);
        if(task.getOwner() != null && task.getOwner().getUsername().equals(getUsername())){
            UserDocument assignee = userService.findById(assigneeId);
            task.setAssignee(assignee);
            return save(task);
        }
        else
            throw new RuntimeException("only owner of task can assign the task");

    }
    @Override
    public TaskDocument unAssignTask(String taskId) {
        TaskDocument task = findById(taskId);
        if(task.getOwner() != null && task.getOwner().getUsername().equals(getUsername())){
            task.setAssignee(null);
            return save(task);
        }
        else
            throw new RuntimeException("only owner of task can remove assignment");
    }

    @Override
    public void deleteTask(String id) {
        TaskDocument task = findById(id);
        if(task.getOwner() != null && task.getOwner().getUsername().equals(getUsername()))
            taskRepository.deleteById(id);
        else
            throw new RuntimeException("only owner of task can delete the task");


    }
}
