package com.mydigipay.todo.services;

import com.mydigipay.todo.models.TaskDocument;

import java.util.List;

public interface TaskService {
    TaskDocument save(TaskDocument taskDocument);

    TaskDocument update(TaskDocument taskDocument);

    TaskDocument create(TaskDocument taskDocument);

    TaskDocument findById(String id);

    List<TaskDocument> getUsersTasks(String type);

    TaskDocument assignTask(String taskId, String assigneeId);

    TaskDocument unAssignTask(String taskId);

    void deleteTask(String id);
}
