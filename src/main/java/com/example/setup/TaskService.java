package com.example.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getByTitle(String title){
        return taskRepository.findByTitle(title);
    }

    public List<Task> getByStatus(Boolean status){
        return taskRepository.findByStatus(status);
    }
}
