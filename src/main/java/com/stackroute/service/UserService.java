package com.stackroute.service;

import com.stackroute.MuzixApplication;
import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    public User saveUser(User user) throws UserAlreadyExistsException;
    public List<User> getAllUsers();
    public User updateUser(User user,int id) throws UserAlreadyExistsException;
    public boolean deleteuser(int id)throws UserNotFoundException;
    public List<MuzixApplication> userByName(String name);
}
