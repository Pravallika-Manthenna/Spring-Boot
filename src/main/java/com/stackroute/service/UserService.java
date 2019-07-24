package com.stackroute.service;

import com.stackroute.MuzixApplication;
import com.stackroute.domain.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);
    public List<User> getAllUsers();
    public User updateUser(User user,int id) throws Exception;
    public boolean deleteuser(int id)throws Exception;
    public List<MuzixApplication> userByName(String name);
}
