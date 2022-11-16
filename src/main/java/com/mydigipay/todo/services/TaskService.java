package com.mydigipay.todo.services;

import com.mydigipay.todo.models.TaskDocument;

import java.util.List;

public interface TaskService {
    TaskDocument save(TaskDocument taskDocument);

    TaskDocument create(TaskDocument taskDocument, String userId);

    TaskDocument findById(String id);

    List<TaskDocument> getUsersTasks(String type, String userId);

    TaskDocument assignTask(String taskId, String assigneeId);

    TaskDocument unAssignTask(String taskId);

    void deleteTask(String id);
}
