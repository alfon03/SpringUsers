package com.user.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.user.model.User;
import com.user.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // MOSTRAR INDEX
    @GetMapping("/")
    public String redirectToIndex() {
        return "index";
    }

    // MOSTRAR USUARIOS TABLA
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    
    // ADD
    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("action", "add");
        return "formUser";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user, Model model) {
        User newUser = userService.save(user);
        String errorMessage = validateUser(user);
        if (errorMessage != null || newUser == null) {
            model.addAttribute("error", errorMessage);
            model.addAttribute("action", "add");
            return "formUser";
        } else {
            model.addAttribute("success", "Usuario añadido correctamente.");
            return "redirect:/users";
        }
    }

    // Edit

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        Optional<User> userOpt = userService.findById(id);
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "Usuario no encontrado.");
            return "error";
        }
        model.addAttribute("user", userOpt.get());
        model.addAttribute("action", "edit");
        return "formUser";  
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable Integer id, @ModelAttribute User updatedUser, Model model) {
        Optional<User> userOpt = userService.findById(id);
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "Usuario no encontrado.");
            return "error";  
        }
        
        User user = userOpt.get();
        userService.edit(id, updatedUser);
        
        model.addAttribute("success", "Usuario editado correctamente.");
        return "redirect:/users";  
    }

    
    // Delete
    @GetMapping("/users/delete/{id}")
    public String showDeleteConfirmation(@PathVariable Integer id, Model model) {
        Optional<User> userOpt = userService.findById(id);
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "Usuario no encontrado.");
            return "error";
        }
        model.addAttribute("user", userOpt.get());
        model.addAttribute("action", "delete");
        return "formUser";  
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id, Model model) {
        Optional<User> userOpt = userService.findById(id);
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "Usuario no encontrado.");
            return "error";
        }
        userService.delete(id);
        model.addAttribute("success", "Usuario eliminado correctamente.");
        return "redirect:/users";
    }

    
    // view
    @GetMapping("/users/view/{id}")
    public String showView(@PathVariable Integer id, Model model) {
        Optional<User> userOpt = userService.findById(id);
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "Usuario no encontrado.");
            return "error";
        }
        model.addAttribute("user", userOpt.get());
        model.addAttribute("action", "view");
        return "formUser";  
    }
    
    // VALIDAR DATOS
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
}
