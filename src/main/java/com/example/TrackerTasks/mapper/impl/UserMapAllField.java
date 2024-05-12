package com.example.TrackerTasks.mapper.impl;

import com.example.TrackerTasks.entity.User;
import com.example.TrackerTasks.mapper.UserMapper;
import com.example.TrackerTasks.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapAllField implements UserMapper {
    @Override
    public User modelToUser(UserModel model) {
        if (model == null) {
            return null;
        }
        User user = new User();
        user.setEmail(model.getEmail());
        user.setUsername(model.getUsername());
        return user;
    }

    @Override
    public UserModel userToModel(User user) {
        if (user == null) {
            return null;
        }
        UserModel model = new UserModel();

        model.setUsername(user.getUsername());
        model.setEmail(user.getEmail());
        return model;
    }
}
