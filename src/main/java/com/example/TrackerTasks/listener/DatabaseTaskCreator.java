


package com.example.TrackerTasks.listener;

import com.example.TrackerTasks.entity.Task;
import com.example.TrackerTasks.entity.User;

import com.example.TrackerTasks.entity.status.TaskStatus;
import com.example.TrackerTasks.mapper.TaskMapper;
import com.example.TrackerTasks.mapper.UserMapper;
import com.example.TrackerTasks.model.TaskModel;
import com.example.TrackerTasks.model.TaskModelCreate;
import com.example.TrackerTasks.model.UserModel;

import com.example.TrackerTasks.repository.UserRepository;
import com.example.TrackerTasks.service.TaskService;
import com.example.TrackerTasks.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import org.springframework.stereotype.Component;


import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;

import java.util.Optional;
import java.util.Set;


@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseTaskCreator {

    private final UserService userService;
    private final UserMapper userMapper;
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;


    @EventListener(ApplicationStartedEvent.class)
    public void createTaskData() {
        UserModel author = new UserModel();
        author.setUsername("SergeyAuthor");
        author.setEmail("serg1978@mail.ru");
        User userAuthor = userService.save(userMapper.modelToUser(author)).block();

        UserModel assignee = new UserModel();
        assignee.setUsername("IvanAssignee");
        assignee.setEmail("ivan1999@mail.ru");
        User userAssignee = userService.save(userMapper.modelToUser(assignee)).block();

        UserModel observer = new UserModel();
        observer.setUsername("OlegObserver");
        observer.setEmail("oleg1978@mail.ru");
        User userObserver = userService.save(userMapper.modelToUser(observer)).block();

        TaskModelCreate newTask = new TaskModelCreate();

        newTask.setName("The task of creation");
        newTask.setDescription("people who had seen him were able to give a description");
        newTask.setAuthorId(userAuthor.getId());
        newTask.setAssigneeId(userAssignee.getId());

        Set<String> observerIds = new HashSet<>();

        observerIds.add(userObserver.getId());
        newTask.setObserverIds(observerIds);


        taskService.save(taskMapper.taskModeltoTask(taskModelCreateToTaskModel(newTask))).block();
        List<Task> taskList = taskService.findAll().collectList().block();
        taskList.forEach(task -> System.out.println(task.toString()));

    }

    public User findUserById(String id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).block());
        if (user.isPresent()) {
            return user.get();
        } else log.info(MessageFormat.format("User with ID {0} not found", id));
        return null;
    }

    public TaskModel taskModelCreateToTaskModel(TaskModelCreate modelCreate) {
        if (modelCreate == null) {
            return null;
        }
        TaskModel taskModel = new TaskModel();

        taskModel.setName(modelCreate.getName());
        taskModel.setDescription(modelCreate.getDescription());
        taskModel.setStatus(TaskStatus.DONE);
        taskModel.setAuthorId(modelCreate.getAuthorId());
        taskModel.setAssigneeId(modelCreate.getAssigneeId());
        taskModel.setObserverIds(modelCreate.getObserverIds());

        User author = findUserById(modelCreate.getAuthorId());
        taskModel.setAuthor(userMapper.userToModel(author));

        User assignee = findUserById(modelCreate.getAssigneeId());

        taskModel.setAssignee(userMapper.userToModel(assignee));

        Set<UserModel> observers = new HashSet<>();
        modelCreate.getObserverIds().forEach(id -> observers.add(userMapper.userToModel(userService.findById(id).block())));
        taskModel.setObservers(observers);

        return taskModel;
    }

}


