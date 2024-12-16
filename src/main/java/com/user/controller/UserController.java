package com.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.user.model.User;
import com.user.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, Model model) {
        if (!isValidUser(user)) {
            model.addAttribute("error", "Invalid user data. Please check your inputs.");
            return "add-user";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id, Model model) {
        if (!userService.delete(id)) {
            model.addAttribute("error", "User not found.");
            return "error";
        }
        return "redirect:/users";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception ex) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    private boolean isValidUser(User user) {
        return user.getName().matches("[A-Za-z]+") &&
               user.getSurname().matches("[A-Za-z]+") &&
               !user.getAddress().isEmpty() &&
               user.getPhone().matches("\\d{10}") &&
               !user.getLocality().isEmpty() &&
               !user.getProvince().isEmpty();
    }
}
