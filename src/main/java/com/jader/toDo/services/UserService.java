package com.jader.toDo.services;

import com.jader.toDo.dto.UserRequestDTO;
import com.jader.toDo.entities.User;
import com.jader.toDo.repositories.UserRepository;
import com.jader.toDo.services.exceptions.DuplicateResourceException;
import com.jader.toDo.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public User insert(UserRequestDTO user) {
        try {
            return repository.save(user.toUser());
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("Email " + user.getEmail() + " already exists.");
        }
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
