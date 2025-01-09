package com.jader.toDo.dto;

import com.jader.toDo.entities.User;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;

    public UserResponseDTO() {
    }

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public User toUser() {
        User user = new User();

        user.setId(this.id);
        user.setEmail(this.email);
        user.setName(this.name);

        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
