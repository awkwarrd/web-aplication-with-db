package com.example.bd_project.controller;

import com.example.bd_project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.bd_project.dto.UserDto;
import com.example.bd_project.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserFormController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String showUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userDto", new UserDto());
        return "users-table-and-form";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("userDto") UserDto userDto) {
        userService.createUser(userDto);
        return "redirect:/users"; // Перезагрузка страницы после добавления
    }

    @PostMapping("{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        model.addAttribute("userDto", userDto);
        model.addAttribute("userId", id);
        return "edit-user-form";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("userDto") UserDto userDto) {
        userService.updateUser(id, userDto);
        return "redirect:/users";
    }
}
