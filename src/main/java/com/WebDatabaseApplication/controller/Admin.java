package com.WebDatabaseApplication.controller;

import com.WebDatabaseApplication.entity.User;
import com.WebDatabaseApplication.repository.UserRepo;
import com.WebDatabaseApplication.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class Admin{
    @Autowired
    private UserService userService;
    @Autowired
    UserRepo userRepo;


    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @RequestMapping("/admin/edit")
    public String update(
            @RequestParam("id") User user,
            @RequestParam("username") String username,
            Model model){
         user.setUsername(username);
        userRepo.save(user);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/temp")
    public String przekieruj(
            @RequestParam("id") Long id, Model model
    )  {
        model.addAttribute("user", userRepo.findById(id));
        return "editUser";
    }

    @GetMapping("/admin/gt/{userId}")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.userList(userId));
        return "admin";
    }
}