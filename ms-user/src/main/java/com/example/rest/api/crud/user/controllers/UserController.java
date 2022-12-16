package com.example.rest.api.crud.user.controllers;

import com.example.rest.api.crud.user.repositories.UserRepository;
import com.example.rest.api.crud.user.models.UserModel;
import com.example.rest.api.crud.user.publish.QueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    UserRepository repository;

    QueueSender queueSender;

    @Autowired
    public UserController(UserRepository repository, QueueSender queueSender) {
        this.repository = repository;
        this.queueSender = queueSender;
    }

    @GetMapping("/user")
    public List<UserModel> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("/usersave")
    public UserModel saveUser(@RequestBody UserModel userModel) {
        queueSender.send(userModel.getEmail());
        return repository.save(userModel);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
