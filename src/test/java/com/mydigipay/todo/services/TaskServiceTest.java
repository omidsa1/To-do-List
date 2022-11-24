package com.mydigipay.todo.services;

import com.mydigipay.todo.models.TaskDocument;
import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.repositories.TaskRepository;
import com.mydigipay.todo.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureDataMongo

public class TaskServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;


    @BeforeEach
    void setup(){
        UserDocument owner = new UserDocument("ownerId","owner","ownerPassword");
        userRepository.save(owner);

        UserDocument assignee = new UserDocument("assigneeId","assignee","assigneePassword");
        userRepository.save(assignee);

        TaskDocument taskDocument = new TaskDocument("taskId","task1","doing task1","to do",owner,assignee);
        taskRepository.save(taskDocument);


        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("owner", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Test
    void doesCreateTask(){
        TaskDocument task = new TaskDocument();
        task.setId("taskId2");
        task.setTitle("task2");
        task.setDescription("doing task2");
        task.setStatus("to do");

        taskService.create(task);
        assertTrue(taskRepository.findAll().size() == 2);
        assertEquals("owner",taskRepository.findById("taskId2").get().getOwner().getUsername());
        assertNull(taskRepository.findById("taskId2").get().getAssignee());

    }

    @Test
    void canOwnerUpdateTask(){
        TaskDocument task = taskRepository.findById("taskId").get();
        task.setStatus("in progress");
        task.setTitle("task1");
        task.setDescription("doing task1");
        UserDocument assigneeBeforeUpdate = task.getAssignee();
        UserDocument ownerBeforeUpdate = task.getOwner();
        taskService.update(task);
        task = taskRepository.findById("taskId").get();

       assertEquals(ownerBeforeUpdate,task.getOwner());
       assertEquals(assigneeBeforeUpdate,task.getAssignee());
       assertEquals("in progress",task.getStatus());
       assertEquals("task1",task.getTitle());
       assertEquals("doing task1",task.getDescription());
    }


    @Test
    void canAssigneeUpdateTask(){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("assignee", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        TaskDocument task = taskRepository.findById("taskId").get();
        task.setStatus("in progress");
        task.setTitle("task1");
        task.setDescription("doing task1");
        UserDocument assigneeBeforeUpdate = task.getAssignee();
        UserDocument ownerBeforeUpdate = task.getOwner();
        taskService.update(task);
        task = taskRepository.findById("taskId").get();

        assertEquals(ownerBeforeUpdate,task.getOwner());
        assertEquals(assigneeBeforeUpdate,task.getAssignee());
        assertEquals("in progress",task.getStatus());
        assertEquals("task1",task.getTitle());
        assertEquals("doing task1",task.getDescription());
    }

    @Test
    void canOthersUpdateTask(){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("rana", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        TaskDocument task = taskRepository.findById("taskId").get();
        task.setStatus("in progress");
        task.setTitle("task1");
        task.setDescription("doing task1");

        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> taskService.update(task));
        assertTrue(ex.getMessage().contains("just owner or assignee can update the task"));
    }


    @Test
    void doesFindById(){
        TaskDocument task = taskService.findById("taskId");

        UserDocument owner = userRepository.findById("ownerId").get();
        UserDocument assignee = userRepository.findById("assigneeId").get();

        assertEquals(owner,task.getOwner());
        assertEquals(assignee,task.getAssignee());
        assertEquals("to do",task.getStatus());
        assertEquals("task1",task.getTitle());
        assertEquals("doing task1",task.getDescription());
    }

    @Test
    void doesFindByInvalidId(){
        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> taskService.findById("invalidTaskId"));
        assertTrue(ex.getMessage().contains("task not found"));
    }

    @Test
    void canGetTasksWithInvalidTypeParameter(){
        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> taskService.getUsersTasks("parameter"));
        assertTrue(ex.getMessage().contains("type parameter should be assigned or created"));
    }


    @Test
    void canGetTasksWithValidTypeParameter(){
        List<TaskDocument> created = taskService.getUsersTasks("created");
        assertIterableEquals(taskRepository.findAll(),created);

        List<TaskDocument> assigned = taskService.getUsersTasks("assigned");
        assertTrue(assigned.isEmpty());
    }

    @Test
    void canOthersAssignTask(){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("rana", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> taskService.assignTask("taskId", "assigneeId"));
        assertTrue( ex.getMessage().contains("only owner of task can assign the task"));
    }

    @Test
    void canAssignByInvalidTaskId(){
        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> taskService.assignTask("invalidTaskId", "assigneeId"));
        assertTrue( ex.getMessage().contains("task not found"));
    }
    @Test
    void canAssignToInvalidUserId(){
        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> taskService.assignTask("taskId", "invalidAssigneeId"));
        assertTrue( ex.getMessage().contains("user not found"));
    }

    @Test
    void canOthersUnAssignTask(){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("rana", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> taskService.unAssignTask("taskId"));
        assertTrue( ex.getMessage().contains("only owner of task can remove assignment"));
    }

    @Test
    void canOwnerUnAssignTask(){
        taskService.unAssignTask("taskId");
        TaskDocument updatedTask = taskRepository.findById("taskId").get();
        assertNull(updatedTask.getAssignee());

    }


    @AfterEach
    void deleteData(){
        userRepository.deleteAll();
        taskRepository.deleteAll();
    }

}
