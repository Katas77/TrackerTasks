package com.example.TrackerTasks.controller;

import com.example.TrackerTasks.entity.Task;
import com.example.TrackerTasks.mapper.TaskMapper;


import com.example.TrackerTasks.model.TaskModel;
import com.example.TrackerTasks.model.TaskModelCreate;
import com.example.TrackerTasks.model.TaskModelUpdate;
import com.example.TrackerTasks.model.UserModel;
import com.example.TrackerTasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskMapper taskMapper;
    private final TaskService taskService;

    @GetMapping
    public Flux<TaskModel> getAllTasks() {
        return taskService.findAll().map(taskMapper::taskToTaskModel);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TaskModel>> getById(@PathVariable String id) {
        return taskService.findById(id)
                .map(taskMapper::taskToTaskModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Mono<ResponseEntity<Task>> createTask(@RequestBody TaskModelCreate model) {
        return taskService.save(taskMapper.taskModelCreateToTask(model))
                .map(ResponseEntity::ok);

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<TaskModelUpdate>> updateItem(@PathVariable String id, @RequestBody TaskModelUpdate model) {
        return taskService.update(id, taskMapper.taskModelUpdateToTask(model))
                .map(taskMapper::TaskToTaskModelUpdate)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PutMapping("/{idTask}/{observerId}")
    public Mono<ResponseEntity<TaskModel>> addObserver(@PathVariable String idTask, @PathVariable String observerId) {
        return taskService.addObserver(idTask, observerId)
                .map(taskMapper::taskToTaskModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteItem(@PathVariable String id) {
        return taskService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));

    }


}
