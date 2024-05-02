package com.example.setup;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static TaskDTO convertToTaskDTO(Task task){
        return new TaskDTO(task.getTitle(),task.getStatus());
    }
    public static List<TaskDTO> convertToTaskDTO(List<Task> task){
        List<TaskDTO> tasks = new ArrayList<>();
        task.forEach(t -> tasks.add(convertToTaskDTO(t)));
        return tasks;
    }
}
