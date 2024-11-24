package com.capgemini.wsb.fitnesstracker.user.api;


public interface UserService {


    void createUser(User user);


    User updateUser(User user);


    void deleteUser(Long userId);
}
