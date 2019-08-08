package com.stackroute.service;

import com.stackroute.domain.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user) throws Exception;
    public List<User> getAllUsers();
    public User updateUser(User user,int id) throws Exception;
    public boolean deleteuser(int id)throws Exception;
}
