package com.example.setup.service;

import com.example.setup.object.Task;
import com.example.setup.object.TaskDTO;
import com.example.setup.object.UserTaskDTO;
import com.example.setup.respository.TaskRepository;
import com.example.setup.util.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDTO> getTasks(Long userId){
        var res = taskRepository.findByUserId(userId);
        return Utils.convertToTaskDTO(res);
    }

    public TaskDTO getTask(UserTaskDTO user){
        var res = taskRepository.findByIdAndUserId(user.getTaskId(),user.getUserId());
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

    public Task createTask(TaskDTO dto){
        var entity = Utils.convertToTaskEntity(dto);
        return taskRepository.save(entity);
    }
    public Task updateTask(Task entity,TaskDTO dto){
        entity.setTitle(dto.getTitle());
        entity.setStatus(dto.getStatus());

        return taskRepository.save(entity);
    }

    @Transactional
    public String deleteTask(UserTaskDTO user){
        var res = taskRepository.findByIdAndUserId(user.getTaskId(),user.getUserId());
        if (res == null)
            return "Object Not Found";
        else
            taskRepository.deleteByIdAndUserId(user.getTaskId(),user.getUserId());
        return "Object Deleted";
    }
    public TaskDTO persistTask(TaskDTO dto){
        var entity = taskRepository.findByIdAndUserId(dto.getId(),dto.getUserId());

        if(entity == null)
            entity = createTask(dto);
        else
            entity = updateTask(entity,dto);

        return Utils.convertToTaskDTO(entity);
    }

}
