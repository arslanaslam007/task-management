package com.example.setup;

import com.example.setup.object.Task;
import com.example.setup.object.TaskDTO;
import com.example.setup.respository.TaskRepository;
import com.example.setup.service.TaskService;
import com.example.setup.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TaskServiceTests {
    @InjectMocks
    TaskService taskService;
    @Mock
    TaskRepository taskRepository;

    @Test
    public void getAllTaskPositiveFlow(){
        List<Task> tasks =  new ArrayList<>();
        var task = new Task();
        task.setId(1L);
        task.setStatus(true);
        tasks.add(task);
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        var res = taskService.getAllTasks();
        Assertions.assertNotNull(res);
    }
    @Test
    public void getByTitlePositiveFlow(){
        List<Task> tasks =  new ArrayList<>();
        var task = new Task();
        task.setId(1L);
        task.setStatus(true);
        tasks.add(task);
        Mockito.when(taskRepository.findByTitleContaining(Mockito.anyString())).thenReturn(tasks);

        var res = taskService.getByTitle("res");
        Assertions.assertNotNull(res);
    }
    @Test
    public void getByStatusPositiveFlow(){
        List<Task> tasks =  new ArrayList<>();
        var task = new Task();
        task.setId(1L);
        task.setStatus(true);
        tasks.add(task);
        Mockito.when(taskRepository.findByStatus(Mockito.anyBoolean())).thenReturn(tasks);

        var res = taskService.getByStatus(true);
        Assertions.assertNotNull(res);
    }
    @Test
    public void deleteTaskPositiveFlow(){
        Mockito.doNothing().when(taskRepository).deleteById(Mockito.anyLong());

        var res = taskService.deleteTask(1L);
        Assertions.assertNotNull(res);
    }
    @Test
    public void getTaskPositiveFlow(){
        var entity = new Task();
        Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
        var res = taskService.getTask(1L);
        Assertions.assertNotNull(res);
    }
    @Test
    public void persistTaskPositiveFlow(){
        var entity = new Task();
        var dto = new TaskDTO();

        Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(entity);

        var res = taskService.persistTask(dto);
        Assertions.assertNotNull(res);
    }
}
