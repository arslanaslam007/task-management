package com.example.setup.util;


import com.example.setup.object.Task;
import com.example.setup.respository.TaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInit {
    @Autowired
    private TaskRepository taskRepository;

    @PostConstruct
    private void init(){
        Task t1 = new Task("write application",true);
        Task t2 = new Task("test application",true);

        taskRepository.save(t1);
        taskRepository.save(t2);
    }
}
