package com.jader.toDo.controllers;

import com.jader.toDo.dto.TaskResponseDTO;
import com.jader.toDo.dto.TaskCreateRequestDTO;
import com.jader.toDo.entities.Task;
import com.jader.toDo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id) {
        TaskResponseDTO obj = new TaskResponseDTO(service.findById(id));

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping()
    public ResponseEntity<List<TaskResponseDTO>> findAll() {
        List<TaskResponseDTO> taskList = service.findAll().stream().map(TaskResponseDTO::new).toList();

        return ResponseEntity.ok().body(taskList);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> insert(@RequestBody @Valid TaskCreateRequestDTO obj) {
        Task task = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(new TaskResponseDTO(task));
    }
}