package com.example.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/title")
    public List<Task> findByTitle(@RequestParam String title){
        return taskService.getByTitle(title);
    }

    @GetMapping("/status")
    public List<Task> getByStatus(@RequestParam Boolean status){
        return taskService.getByStatus(status);
    }
}
