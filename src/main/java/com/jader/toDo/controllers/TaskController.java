package com.jader.toDo.controllers;

import com.jader.toDo.dto.TaskResponseDTO;
import com.jader.toDo.dto.TaskCreateRequestDTO;
import com.jader.toDo.dto.TaskUpdateRequestDTO;
import com.jader.toDo.entities.Task;
import com.jader.toDo.services.TaskService;
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

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        service.deleteById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Deleted with success");

        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<TaskResponseDTO> update(@RequestBody @Valid TaskUpdateRequestDTO obj, @PathVariable Long id) {
        Task task = service.update(obj, id);

        return ResponseEntity.ok(new TaskResponseDTO(task));
    }
}