package com.stackroute.service;

import com.stackroute.MuzixApplication;
import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@PropertySource("application.properties")
public class UserServiceImpl implements UserService , ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {

    @Value("${user.1.firstName:default}")
    String firstName1;
    @Value("${user.1.lastName:default}")
    String lastName1;
    @Value("${user.1.age:default}")
    int age1;
    @Value("${user.2.firstName:default}")
    String firstName2;
    @Value("${user.2.lastName:default}")
    String lastName2;
    @Value("${user.2.age:default}")
    int age2;

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.existsById(user.getId())){
            throw new UserAlreadyExistsException("user Already exists");
        }
        User savedUser = userRepository.save(user);
        if(savedUser==null){
            throw new UserAlreadyExistsException("user already exists");
        }

        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }


    public User updateUser(User user, int id) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            throw new UserAlreadyExistsException("user id not found");


        user.setId(id);

        return userRepository.save(user);

    }

    @Override
    public boolean deleteuser( int id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            throw new UserNotFoundException("user id not found");

        userRepository.delete(userOptional.get());
        return true;
    }

    @Override
    public List<MuzixApplication> userByName(String name)
    {
        return userRepository.userByName(name);
    }

    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        userRepository.save(new User(1,firstName1,lastName1,age1));
        userRepository.save(new User(2,firstName2,lastName2,age2));

    }
}

