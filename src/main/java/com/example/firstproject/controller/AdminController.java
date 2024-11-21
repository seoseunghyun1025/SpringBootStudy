package com.example.firstproject.controller;

import com.example.firstproject.entity.User;
import com.example.firstproject.entity.UserRole;
import com.example.firstproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping("/admin/users")
    public String listUsers(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        User loginUser = userService.getLoginUserById(userId);
        if (loginUser == null || !loginUser.getRole().equals(UserRole.ADMIN)) {
            return "redirect:/session-login/login";
        }
        List<User> users = userService.getAllUsers();
        System.out.println(users.get(1).getId());
        model.addAttribute("users", users);
        return "admin/userList";
    }

    @GetMapping("/admin/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        User loginUser = userService.getLoginUserById(userId);
        if (loginUser == null || !loginUser.getRole().equals(UserRole.ADMIN)) {
            return "redirect:/session-login/login";
        }
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/editUser";
    }

    @PostMapping("/admin/users/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User user, @SessionAttribute(name = "userId", required = false) Long userId) {
        User loginUser = userService.getLoginUserById(userId);
        if (loginUser == null || !loginUser.getRole().equals(UserRole.ADMIN)) {
            return "redirect:/session-login/login";
        }
        userService.updateUser(id, user);
        return "redirect:/admin/users/edit/" + id;
    }

    @PostMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, @SessionAttribute(name = "userId", required = false) Long userId) {
        User loginUser = userService.getLoginUserById(userId);
        if (loginUser == null || !loginUser.getRole().equals(UserRole.ADMIN)) {
            return "redirect:/session-login/login";
        }
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
