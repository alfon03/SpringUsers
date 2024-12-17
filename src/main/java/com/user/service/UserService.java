package com.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.User;
import com.user.repositoy.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
    public List<User> getAll() {
        return userRepository.findAll();
    }
/*
    public Optional<User> findById(String id) {
        return userRepository.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public void save(User user) {
        user.setId(String.valueOf(userRepository.size() + 1));
        userRepository.add(user);
    }

    public boolean delete(String id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
    */
}