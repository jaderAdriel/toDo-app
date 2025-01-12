package com.jader.toDo.services;

import com.jader.toDo.dto.TaskCreateRequestDTO;
import com.jader.toDo.dto.TaskUpdateRequestDTO;
import com.jader.toDo.entities.Task;
import com.jader.toDo.entities.User;
import com.jader.toDo.repositories.TaskRepository;
import com.jader.toDo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
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

    public Task update(TaskUpdateRequestDTO obj, Long id) {
        Task task = this.findById(id);

        // Copia as propiedades do objeto da requisição para a entidade, exceto os nulos
        BeanUtils.copyProperties(obj, task, getNullPropertyNames(obj));

        taskRepository.save(task);

        return task;
    }


    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> obj = taskRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private String[] getNullPropertyNames(TaskUpdateRequestDTO source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        //  Um PropertyDescriptor descreve uma propriedade, incluindo seu nome, tipo, e os métodos getter e setter associados a ela.
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        return Arrays.stream(pds)
                .map(pd -> pd.getName())
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }
}
