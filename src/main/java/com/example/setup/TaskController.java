package com.example.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseMetrics getAllTasks(){
        var res = taskService.getAllTasks();
        return MetricsUtils.convertToOkResponse(res);
    }

    @GetMapping("/id/{id}")
    public ResponseMetrics getTask(@PathVariable Long id){
        var res = taskService.getTask(id);
        return MetricsUtils.convertToOkResponse(res);
    }
    @GetMapping("/title")
    public ResponseMetrics findByTitle(@RequestParam String query){
        var res = taskService.getByTitle(query);
        return MetricsUtils.convertToOkResponse(res);
    }

    @GetMapping("/status")
    public ResponseMetrics getByStatus(@RequestParam Boolean query){
        var res = taskService.getByStatus(query);
        return MetricsUtils.convertToOkResponse(res);
    }

    @PostMapping
    public ResponseMetrics createTask(@RequestBody TaskDTO task){
        var res = taskService.persistTask(task);
        return MetricsUtils.convertToOkResponse(res);
    }

    @PutMapping
    public ResponseMetrics updateTask(@RequestBody TaskDTO task){
        var res = taskService.persistTask(task);
        return MetricsUtils.convertToOkResponse(res);
    }
    @DeleteMapping("/id/{id}")
    public ResponseMetrics deleteTask(@PathVariable Long id){
        var res = taskService.deleteTask(id);
        return MetricsUtils.convertToOkResponse(res);
    }
}
