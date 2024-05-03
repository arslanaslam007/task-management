package com.example.setup;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static TaskDTO convertToTaskDTO(Task task){
        if (task == null)
            return null;
        return new TaskDTO(task.getId(),task.getTitle(),task.getStatus());
    }
    public static List<TaskDTO> convertToTaskDTO(List<Task> task){
        List<TaskDTO> tasks = new ArrayList<>();
        task.forEach(t -> tasks.add(convertToTaskDTO(t)));
        return tasks;
    }
    public static Task convertToTaskEntity(TaskDTO dto){
        if (dto == null)
            return null;
        return new Task(dto.getTitle(),dto.getStatus());
    }
}
