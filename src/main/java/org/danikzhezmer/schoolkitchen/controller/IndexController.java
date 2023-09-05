package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("order", new KitchenOrderDto());
        List<String> listOfGroups = Arrays.asList("Group1", "Group2", "Group3");
        model.addAttribute("listOfGroups", listOfGroups);
        return "order/new_order";

    }
}
