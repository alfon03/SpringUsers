package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.user.model.User;
import com.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToIndex() {
        return "index";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }
/*
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable String id, Model model) {
        if (!userService.delete(id)) {
            model.addAttribute("error", "Usuario no encontrado.");
            return "error";
        }
        return "redirect:/users";
    }

  
    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user, Model model) {
        String errorMessage = validateUser(user);
        if (errorMessage != null) {
            model.addAttribute("error", errorMessage);
            return "add-user";
        }
        userService.save(user);
        return "redirect:/users";
    }

    private String validateUser(User user) {
        if (!user.getName().matches("[A-Za-z]+")) {
            return "El nombre solo puede contener letras.";
        }
        if (!user.getSurname().matches("[A-Za-z]+")) {
            return "El apellido solo puede contener letras.";
        }
        if (user.getAddress().isEmpty()) {
            return "La dirección no puede estar vacía.";
        }
        if (!user.getPhone().matches("\\d{9}")) {
            return "El teléfono debe tener exactamente 9 dígitos.";
        }
        if (user.getLocality().isEmpty()) {
            return "La localidad no puede estar vacía.";
        }
        if (user.getProvince().isEmpty()) {
            return "La provincia no puede estar vacía.";
        }
        if (user.getEdad() <= 0 || user.getEdad() > 18) {
            return "La edad debe estar entre 1 y 18 años.";
        }
        return null;
    }
*/

}
