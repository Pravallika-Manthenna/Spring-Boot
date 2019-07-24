package com.stackroute.service;

import com.stackroute.MuzixApplication;
import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }


    public User updateUser(User user, int id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            throw new Exception("user id not found");


        user.setId(id);

        return userRepository.save(user);

    }

    @Override
    public boolean deleteuser( int id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            throw new Exception("user id not found");

        userRepository.delete(userOptional.get());
        return true;
    }

    @Override
    public List<MuzixApplication> userByName(String name) {
        return userRepository.userByName(name);
    }
}

