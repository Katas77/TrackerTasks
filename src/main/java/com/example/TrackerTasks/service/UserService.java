package com.example.TrackerTasks.service;


import com.example.TrackerTasks.entity.User;
import com.example.TrackerTasks.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;


    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }


    public Flux<User> findByName(String name) {
        return userRepository.findByUsername(name);

    }

    public Mono<User> save(User user) {
        user.setId(UUID.randomUUID().toString().substring(0, 4));
        return userRepository.save(user);
    }

    public Mono<User> update(String id, User user) {
        return findById(id).flatMap(userForUpdate -> {
            if (StringUtils.hasText(user.getUsername())) {
                userForUpdate.setUsername(user.getUsername());
            }
            if (user.getEmail() != null) {
                userForUpdate.setEmail(user.getEmail());
            }
            return userRepository.save(userForUpdate);
        });

    }


    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }


    public Mono<Boolean> sendEmail(String userId) {
        return findById(userId)
                .flatMap(user -> {
                    System.out.println("Sending email to: " + user.getEmail());
                    return Mono.just(true);
                })
                .defaultIfEmpty(false);
    }


}
