package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.model.ModelData;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model {

    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();
    @Override
    public ModelData getModelData() { return modelData; }
    @Override
    public void loadUsers() {
        modelData.setUsers(getAllUsers());
        modelData.setDisplayDeletedUserList(false);
    }

    @Override
    public void loadDeletedUsers() {
        modelData.setUsers(userService.getAllDeletedUsers());
        modelData.setDisplayDeletedUserList(true);
    }

    public void loadUserById(long userId) { modelData.setActiveUser(userService.getUsersById(userId)); }

    @Override
    public void deleteUserById(long id) {
        userService.deleteUser(id);
        modelData.setUsers(getAllUsers());
    }

    private List<User> getAllUsers() { return  userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1, 100)); }

    public void changeUserData(String name, long id, int level) {
        userService.createOrUpdateUser(name, id, level);
        modelData.setUsers(getAllUsers());
    }
}