package com.example.setup.service;

import com.example.setup.object.Task;
import com.example.setup.object.TaskDTO;
import com.example.setup.respository.TaskRepository;
import com.example.setup.util.Utils;
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

    public TaskDTO persistTask(TaskDTO dto){
        var entity = findById(dto.getId());

        if(entity == null)
            entity = createTask(dto);
        else
            entity = updateTask(entity,dto);

        return Utils.convertToTaskDTO(entity);
    }
    public Task findById(Long id){
        if(id == null)
            return null;

        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(TaskDTO dto){
        var entity = Utils.convertToTaskEntity(dto);
        return taskRepository.save(entity);
    }
    public Task updateTask(Task entity,TaskDTO dto){
        entity.setTitle(dto.getTitle());
        entity.setStatus(dto.getStatus());

        return taskRepository.save(entity);
    }

    public String deleteTask(Long id){
        var res = findById(id);
        if (res == null)
            return "Object Not Found";
        else
            taskRepository.deleteById(id);
        return "Object Deleted";
    }

    public TaskDTO getTask(Long id){
        var res = findById(id);
        return Utils.convertToTaskDTO(res);
    }
}
