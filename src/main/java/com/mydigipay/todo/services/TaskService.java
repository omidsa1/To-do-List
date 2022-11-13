package com.mydigipay.todo.services;

import com.mydigipay.todo.models.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    public List<TaskDTO> getTasks(String type) {
        return null;
    }

    public TaskDTO updateTask(TaskDTO task) {
        return task;
    }

    public TaskDTO assignTask(Integer taskId, Integer assigneeId) {
        return null;
    }

    public void deleteTask(Integer taskId) {
    }
}
