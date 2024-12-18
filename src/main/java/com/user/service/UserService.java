package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.User;
import com.user.repositoy.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAll() {
        return userRepository.findAll();
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);  
    }

    public boolean delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);  
            return true;
        }
        return false;
    }

    public User edit(Integer id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            
            existingUser.setName(updatedUser.getName());
            existingUser.setSurname(updatedUser.getSurname());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setEdad(updatedUser.getEdad());
            existingUser.setLocality(updatedUser.getLocality());
            existingUser.setProvince(updatedUser.getProvince());
            existingUser.setAddress(updatedUser.getAddress());;

            return userRepository.save(existingUser);
        }
        
        return null; 
    }
    
}
