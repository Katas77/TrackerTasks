package com.example.TrackerTasks.mapper.impl;

import com.example.TrackerTasks.entity.Task;
import com.example.TrackerTasks.entity.status.TaskStatus;
import com.example.TrackerTasks.mapper.TaskMapper;
import com.example.TrackerTasks.mapper.UserMapper;
import com.example.TrackerTasks.model.TaskModel;
import com.example.TrackerTasks.model.TaskModelCreate;
import com.example.TrackerTasks.model.TaskModelUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TaskMapperAllField implements TaskMapper {
    private final UserMapper userMapper;


    @Override
    public TaskModelUpdate TaskToTaskModelUpdate(Task task) {
        if (task == null) {
            return null;
        }
        TaskModelUpdate taskModelUpdate = new TaskModelUpdate();
        taskModelUpdate.setName(task.getName());
        taskModelUpdate.setDescription(task.getDescription());
        taskModelUpdate.setStatus(task.getStatus());
        taskModelUpdate.setAuthorId(task.getAuthorId());
        taskModelUpdate.setAssigneeId(task.getAssigneeId());
        taskModelUpdate.setObserverIds(task.getObserverIds());


        return taskModelUpdate;

    }

    @Override
    public TaskModel taskToTaskModel(Task task) {
        if (task == null) {
            return null;
        }
        TaskModel taskModel = new TaskModel();
        taskModel.setId(task.getId());


        taskModel.setName(task.getName());
        taskModel.setDescription(task.getDescription());

        taskModel.setCreatedAt(task.getCreatedAt());
        taskModel.setUpdatedAt(task.getUpdatedAt());

        taskModel.setStatus(task.getStatus());
        taskModel.setAuthorId(task.getAuthorId());
        taskModel.setAssigneeId(task.getAssigneeId());
        taskModel.setObserverIds(task.getObserverIds());


        taskModel.setAuthor(userMapper.userToModel(task.getAuthor()));
        taskModel.setAssignee(userMapper.userToModel(task.getAssignee()));
        taskModel.setObservers(userMapper.setUserModels(task.getObservers()));

        return taskModel;
    }


    @Override
    public Task taskModelCreateToTask(TaskModelCreate modelCreate) {
        if (modelCreate == null) {
            return null;
        }
        Task task = new Task();

        task.setName(modelCreate.getName());
        task.setDescription(modelCreate.getDescription());
        task.setStatus(TaskStatus.DONE);
        task.setAuthorId(modelCreate.getAuthorId());
        task.setAssigneeId(modelCreate.getAssigneeId());
        task.setObserverIds(modelCreate.getObserverIds());

        return task;
    }

    @Override
    public Task taskModelUpdateToTask(TaskModelUpdate modelUpdate) {
        if (modelUpdate == null) {
            return null;
        }
        Task task = new Task();

        task.setName(modelUpdate.getName());
        task.setDescription(modelUpdate.getDescription());
        task.setStatus(modelUpdate.getStatus());
        task.setAuthorId(modelUpdate.getAuthorId());
        task.setAssigneeId(modelUpdate.getAssigneeId());
        task.setObserverIds(modelUpdate.getObserverIds());

        return task;
    }

    @Override
    public Task taskModeltoTask(TaskModel model) {
        if (model == null) {
            return null;
        }
        Task task = new Task();
        task.setId(model.getId());


        task.setName(model.getName());
        task.setDescription(model.getDescription());

        task.setCreatedAt(model.getCreatedAt());
        task.setUpdatedAt(model.getUpdatedAt());

        task.setStatus(model.getStatus());
        task.setAuthorId(model.getAuthorId());
        task.setAssigneeId(model.getAssigneeId());
        task.setObserverIds(model.getObserverIds());


        task.setAuthor(userMapper.modelToUser(model.getAuthor()));
        task.setAssignee(userMapper.modelToUser(model.getAssignee()));
        task.setObservers(userMapper.setUsers(model.getObservers()));

        return task;
    }


}
