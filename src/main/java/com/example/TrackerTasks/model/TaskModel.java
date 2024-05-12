package com.example.TrackerTasks.model;


import com.example.TrackerTasks.entity.status.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskModel {

    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private TaskStatus status;

    private String authorId;
    private String assigneeId;
    private Set<String> observerIds;

    private UserModel author;
    private UserModel assignee;
    private Set<UserModel> observers;


}
