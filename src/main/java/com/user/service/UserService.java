package com.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.user.model.User;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public void save(User user) {
        user.setId(String.valueOf(users.size() + 1));
        users.add(user);
    }

    public boolean delete(String id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}