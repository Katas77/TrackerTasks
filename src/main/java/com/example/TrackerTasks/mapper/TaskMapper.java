package com.example.TrackerTasks.mapper;

import com.example.TrackerTasks.entity.Task;
import com.example.TrackerTasks.model.TaskModel;
import com.example.TrackerTasks.model.TaskModelCreate;
import com.example.TrackerTasks.model.TaskModelUpdate;

public interface TaskMapper {
    TaskModelUpdate TaskToTaskModelUpdate(Task task);

    TaskModel taskToTaskModel(Task task);

    Task taskModelCreateToTask(TaskModelCreate modelCreate);

    Task taskModelUpdateToTask(TaskModelUpdate modelUpdate);


    Task taskModeltoTask(TaskModel taskModel);
}

