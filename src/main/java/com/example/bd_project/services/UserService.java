package com.example.bd_project.services;

import com.example.bd_project.controller.UserController;
import com.example.bd_project.dto.UserDto;
import com.example.bd_project.model.User;
import com.example.bd_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    public Logger log = Logger.getLogger(UserController.class.getName());
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserDto userDto) {
        User user = new User(userDto);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, UserDto userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    public List<User> searchByNameOrEmail(String query) {
        return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }

    public long countUsers() {
        return userRepository.count();
    }

    public List<Object[]> getUsersWithGoalsAndTransactionSums(String userName) {
        System.out.println(userName);
        return userRepository.getUsersWithGoalsAndTransactionSums(userName);
    }
}
