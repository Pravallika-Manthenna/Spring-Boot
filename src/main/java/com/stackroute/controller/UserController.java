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
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        ResponseEntity responseEntity;
        try {
            userService.saveUser(user);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (UserAlreadyExistsException exception) {
            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("user")
    public ResponseEntity<?> getAllUsers() {
        ResponseEntity responseEntity;
        try {
            userService.getAllUsers();
            responseEntity = new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception exception1) {
            responseEntity = new ResponseEntity<String>(exception1.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            userService.updateUser(user,id);
            responseEntity = new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.CREATED);
        } catch (Exception exception1) {
            responseEntity = new ResponseEntity<String>(exception1.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @DeleteMapping("user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) throws UserNotFoundException {
        ResponseEntity responseEntity;
        try {
            userService.deleteuser(id);
            responseEntity = new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("userByName")
    public ResponseEntity<?> userByName(@RequestBody String name) {
        ResponseEntity responseEntity;
        try {
            userService.userByName(name);
            responseEntity = new ResponseEntity<String>("user by name", HttpStatus.OK);
        } catch (Exception exception) {
            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}

