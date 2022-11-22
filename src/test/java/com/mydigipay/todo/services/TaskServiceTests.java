package com.mydigipay.todo.services;

import com.mydigipay.todo.models.TaskDocument;
import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class TaskServiceTests {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private static UserDocument user;
    private static TaskDocument task;

    public TaskServiceTests() {
    }

    @BeforeAll
    static void init(){
        user = new UserDocument.Builder().
                id("1L").
                username("test").
                password("test").
                build();

        task = new TaskDocument.Builder().
                id("1L").
                title("test").
                description("test").
                assignee(user).
                owner(user).
                build();

    }

  @Test
    void givenTask_whenSaveTask_thenReturnTask() {
        // given
        given(taskRepository.findById(task.getId())).willReturn(Optional.empty());
        given(taskRepository.save(task)).willReturn(task);

        // when
        TaskDocument savedTask = taskService.save(task);

        // then
        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getId()).isEqualTo(task.getId());
        assertThat(savedTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(savedTask.getDescription()).isEqualTo(task.getDescription());
        assertThat(savedTask.getAssignee()).isEqualTo(task.getAssignee());
        assertThat(savedTask.getOwner()).isEqualTo(task.getOwner());
    }

    void givenTask_whenUpdateTask_thenReturnTask() {
        // given
        given(taskRepository.save(task)).willReturn(task);


        // when
        TaskDocument updatedTask = taskService.save(task);

        // then
        assertThat(updatedTask).isNotNull();
        assertThat(updatedTask.getId()).isEqualTo(task.getId());
        assertThat(updatedTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(updatedTask.getDescription()).isEqualTo(task.getDescription());
        assertThat(updatedTask.getAssignee()).isEqualTo(task.getAssignee());
        assertThat(updatedTask.getOwner()).isEqualTo(task.getOwner());
    }

    @Test
    void givenId_whenFindTaskById_thenReturnTask() {
        // given
        given(taskRepository.findById(task.getId())).willReturn(Optional.of(task));

        // when
        TaskDocument foundTask = taskService.findById(task.getId());

        // then
        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getId()).isEqualTo(task.getId());
        assertThat(foundTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(foundTask.getDescription()).isEqualTo(task.getDescription());
        assertThat(foundTask.getAssignee()).isEqualTo(task.getAssignee());
        assertThat(foundTask.getOwner()).isEqualTo(task.getOwner());
    }

    @Test
    void whenGetUsersTasksWithAssginedType_thenReturnAssignedTasks() {

        // when
        List<TaskDocument> foundTasks = taskService.getUsersTasks("aSsGined", "1L");

        // then
        assertThat(foundTasks).isNotNull();
        assertThat(foundTasks.size()).isEqualTo(1);
        assertThat(foundTasks.get(0).getId()).isEqualTo(task.getId());
        assertThat(foundTasks.get(0).getTitle()).isEqualTo(task.getTitle());
        assertThat(foundTasks.get(0).getDescription()).isEqualTo(task.getDescription());
        assertThat(foundTasks.get(0).getAssignee()).isEqualTo(task.getAssignee());
        assertThat(foundTasks.get(0).getOwner()).isEqualTo(task.getOwner());
    }

    @Test
    void whenGetUsersTasksWithCreatedType_thenReturnCreatedTasks() {

            //given
            given(taskService.assignTask(task.getId(), user.getId())).willReturn(task);

            // when
            List<TaskDocument> foundTasks = taskService.getUsersTasks("cReAtEd", "1L");

            // then
            assertThat(foundTasks).isNotNull();
            assertThat(foundTasks.size()).isEqualTo(1);
            assertThat(foundTasks.get(0).getId()).isEqualTo(task.getId());
            assertThat(foundTasks.get(0).getTitle()).isEqualTo(task.getTitle());
            assertThat(foundTasks.get(0).getDescription()).isEqualTo(task.getDescription());
            assertThat(foundTasks.get(0).getAssignee()).isEqualTo(task.getAssignee());
            assertThat(foundTasks.get(0).getOwner()).isEqualTo(task.getOwner());
    }


}
