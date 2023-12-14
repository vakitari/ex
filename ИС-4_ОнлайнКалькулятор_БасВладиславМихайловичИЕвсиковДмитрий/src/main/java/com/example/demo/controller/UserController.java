package com.example.demo.controller;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final PasswordEncoder passwordEncoder;

    private  final UserService service;
    @GetMapping("/register")
    public String loadPage(Model model){
        model.addAttribute("user", new User());
        return "regPage";
    }

    @PostMapping("/register")
    public String createUser(User user){
        service.createUser(user);
        return "redirect:/login";

    }
    @GetMapping("/login")
    public String login(){return "login";}


    @GetMapping("/admin/")
    public String reder(Model model){
        model.addAttribute("users",service.getAll());
        return "edit";
    }
    @GetMapping("/admin/editUser")
    public String editUserForm(Principal principal, Model model) {

        User user = service.findByUserName(principal.getName());
        List<Role> allRoles = service.getAllRoles();

        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);

        return "editUser";
    }
    @PostMapping("/editUser")
    public String editUser(@ModelAttribute User user) {

        User existingUser = service.getById(user.getId());


        if (existingUser != null) {

            String originalPassword = existingUser.getPassword();
            existingUser.setUsername(user.getUsername());
            existingUser.setRoles(user.getRoles());

            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                existingUser.setPassword(originalPassword);
            } else {

                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            service.add(existingUser);
        }

        return "redirect:/admin/";
    }
}
