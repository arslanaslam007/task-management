package com.example.setup;


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
