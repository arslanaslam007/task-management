package com.example.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDTO> getAllTasks(){
        var res = taskRepository.findAll();
        return Utils.convertToTaskDTO(res);
    }

    public List<TaskDTO> getByTitle(String title){
        var res= taskRepository.findByTitleContaining(title);
        return Utils.convertToTaskDTO(res);
    }

    public List<TaskDTO> getByStatus(Boolean status){
        var res = taskRepository.findByStatus(status);
        return Utils.convertToTaskDTO(res);
    }
}
