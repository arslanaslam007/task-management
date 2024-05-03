package com.example.setup;

import com.example.setup.object.TaskDTO;
import com.example.setup.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class TaskControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskService taskService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getAllTasksReturnTasks() throws Exception {
        List<TaskDTO> tasks =  new ArrayList<>();
        TaskDTO task = new TaskDTO();
        task.setId(1L);
        tasks.add(task);
        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/task"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.value.length()").value(1))
                .andExpect(jsonPath("$.value[0].id").value(1));
    }

    @Test
    public void getTaskReturnTask() throws Exception {
        TaskDTO task = new TaskDTO();
        task.setId(1L);
        Mockito.when(taskService.getTask(Mockito.anyLong())).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/id/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.value.id").value(1));
    }
    @Test
    public void getTaskByTitleReturnTask() throws Exception {
        List<TaskDTO> tasks =  new ArrayList<>();
        TaskDTO task = new TaskDTO();
        task.setId(1L);
        task.setTitle("work");
        tasks.add(task);
        Mockito.when(taskService.getByTitle(Mockito.anyString())).thenReturn(tasks);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/title?query=work"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.value.length()").value(1))
                .andExpect(jsonPath("$.value[0].id").value(1));
    }
    @Test
    public void getTaskByStatusReturnTask() throws Exception {
        List<TaskDTO> tasks =  new ArrayList<>();
        TaskDTO task = new TaskDTO();
        task.setId(1L);
        task.setStatus(true);
        tasks.add(task);
        Mockito.when(taskService.getByStatus(Mockito.anyBoolean())).thenReturn(tasks);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/status?query=true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.value.length()").value(1))
                .andExpect(jsonPath("$.value[0].id").value(1));
    }

    @Test
    public void createTaskPositiveFlow() throws Exception {
        var task = new TaskDTO(null,"Test",false);
        var res = objectMapper.writeValueAsString(task);

        Mockito.when(taskService.persistTask(Mockito.any())).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(res))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateTaskPositiveFlow() throws Exception {
        var task = new TaskDTO(1L,"Test",false);
        var res = objectMapper.writeValueAsString(task);

        Mockito.when(taskService.persistTask(Mockito.any())).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(res)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteTaskPositiveFlow() throws Exception {
        var str = "done";
        Mockito.when(taskService.deleteTask(Mockito.anyLong())).thenReturn(str);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/task/id/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
