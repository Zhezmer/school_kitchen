package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserList(Model model){
        model.addAttribute("users", userService.findAll());
        return "user/user_list";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "user/user_card";


    }

@GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return "redirect:/users";
}
//    @GetMapping("/{productId}/delete")
//    public String deleteProduct(@PathVariable Long productId) {
//        productService.deleteById(productId);
//        return "redirect:/products";
//    }


}
