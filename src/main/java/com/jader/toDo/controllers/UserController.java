package com.jader.toDo.controllers;

import com.jader.toDo.dto.UserCreateRequestDTO;
import com.jader.toDo.dto.UserResponseDTO;
import com.jader.toDo.entities.User;
import com.jader.toDo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = service.findAll().stream().map(UserResponseDTO::new).toList();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        User user = service.findById(id);

        return ResponseEntity.ok().body(new UserResponseDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@Valid @RequestBody UserCreateRequestDTO obj) {
        User user = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserResponseDTO(user));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        service.deleteById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Deleted with success");

        return ResponseEntity.ok().body(response);
    }
}
