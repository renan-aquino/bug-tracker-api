package com.api.bugtracker.controllers;

import com.api.bugtracker.dtos.UserDTO;
import com.api.bugtracker.dtos.UserResponseDTO;
import com.api.bugtracker.models.User;
import com.api.bugtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public  ResponseEntity<UserResponseDTO> getUser(@PathVariable String id){
        User user = repository.findUserById(id);
        UserResponseDTO name = new UserResponseDTO(user.getName());
        return ResponseEntity.ok(name);
    }

}
