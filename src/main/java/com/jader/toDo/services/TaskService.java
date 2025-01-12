package com.jader.toDo.services;

import com.jader.toDo.dto.TaskCreateRequestDTO;
import com.jader.toDo.entities.Task;
import com.jader.toDo.entities.User;
import com.jader.toDo.repositories.TaskRepository;
import com.jader.toDo.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public Task insert(TaskCreateRequestDTO obj) {
        User user = userService.findById(obj.getUserId());
        Task task = obj.toTask(user);

        return taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> obj = taskRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
