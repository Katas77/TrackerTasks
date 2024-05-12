package com.example.TrackerTasks.repository;

import com.example.TrackerTasks.entity.Task;

import com.example.TrackerTasks.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TaskRepository extends ReactiveMongoRepository<Task, String> {

    Mono<Task> findByName(String name);

}
