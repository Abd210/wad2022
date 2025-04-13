//userController
package com.wad.firstmvc.controllers;

import com.wad.firstmvc.domain.User;
import com.wad.firstmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/viewUsers";
    }

    @GetMapping("/viewUsers")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "viewUsers";
    }
}
