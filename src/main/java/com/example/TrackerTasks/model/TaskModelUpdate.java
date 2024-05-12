package com.example.TrackerTasks.model;

import com.example.TrackerTasks.entity.status.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskModelUpdate {

    private String name;
    private String description;
    private TaskStatus status;
    private String authorId;
    private String assigneeId;
    private Set<String> observerIds;

}
