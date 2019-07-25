package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        userService.saveUser(user);
        return new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
    }

    @GetMapping("user1")
    public ResponseEntity<?> getAllUsers() {

        userService.getAllUsers();
        System.out.println("entered into get all users");
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id) throws UserAlreadyExistsException {
        userService.updateUser(user, id);
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.CREATED);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) throws UserNotFoundException {
        userService.deleteuser(id);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }

    @GetMapping("userByName")
    public ResponseEntity<?> userByName(@RequestBody String name) throws UserNotFoundException {

        userService.userByName(name);
        return new ResponseEntity<String>("user by name", HttpStatus.OK);

    }
}

