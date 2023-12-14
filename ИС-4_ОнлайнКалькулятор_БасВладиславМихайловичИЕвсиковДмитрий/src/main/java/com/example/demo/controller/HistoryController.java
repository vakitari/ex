package com.example.demo.controller;

import com.example.demo.Entity.History;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Service.HistorySerivce;
import com.example.demo.Service.UserDetailsImp;
import com.example.demo.Service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;


@Controller
@RequestMapping("/his")
public class HistoryController {
    private final HistorySerivce service;

    private  final UserService userService;
    private HistoryController(HistorySerivce serivce, UserService userService){this.service = serivce;
        this.userService = userService;
    }

@PostMapping("/add")
    public String addHistory(History history){
    service.save(history);
    System.out.println("sdfsd");

      return "redirect:/his/home";
    }
    @GetMapping("/home")
    public String allHis(Principal principal,Model model){
        model.addAttribute("history",new History());

        String userName = principal.getName();
        User user1 = userService.findByUserName(userName);
        Set<Role> id = user1.getRoles();
        System.out.println(id);
        Optional<History>  history = service.findById(1L);

        History his = history.orElse(new History());

        model.addAttribute("his", his);

        if (principal == null) {
            model.addAttribute("user", new User());
        }else {
            User user = userService.findByUserName(principal.getName());
            model.addAttribute("user", user);
        }
            return "index";
        }

        @GetMapping("")
    public String del(Model model,Principal principal){

            User user = userService.findByUserName(principal.getName());
            Iterable<History> history = service.findByUserId(user.getId());
            model.addAttribute("his", history);

        return "history";}
}
