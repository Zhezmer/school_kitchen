package org.danikzhezmer.schoolkitchen.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.danikzhezmer.schoolkitchen.entity.Role;
import org.danikzhezmer.schoolkitchen.entity.User;
import org.danikzhezmer.schoolkitchen.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);

        //присвоение роли юзера всем
        Role role = new Role(1L, "ROLE_USER");
        user.setRole(role);

        userService.save(user);
        return "redirect:/login";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }
}
