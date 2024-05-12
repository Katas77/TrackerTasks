package com.example.TrackerTasks.controller;


import com.example.TrackerTasks.entity.User;
import com.example.TrackerTasks.mapper.UserMapper;
import com.example.TrackerTasks.model.UserModel;
import com.example.TrackerTasks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping
    public Flux<User> getAllItems() {
        return userService.findAll();
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserModel>> getById(@PathVariable String id) {
        return userService.findById(id)
                .map(userMapper::userToModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @GetMapping("/by-name")
    public Flux<ResponseEntity<UserModel>> getItemByName(@RequestParam String name) {
        return userService.findByName(name)
                .map(userMapper::userToModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<UserModel>> createUser(@RequestBody UserModel model) {
        return userService.save(userMapper.modelToUser(model))
                .map(userMapper::userToModel)
                .map(ResponseEntity::ok);

    }


    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserModel>> updateUser(@PathVariable String id, @RequestBody UserModel model) {
        return userService.update(id, userMapper.modelToUser(model))
                .map(userMapper::userToModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return userService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));

    }


}
