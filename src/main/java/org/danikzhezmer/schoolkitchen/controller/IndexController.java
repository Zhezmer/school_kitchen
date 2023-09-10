package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.service.KitchenOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {
    KitchenOrderService kitchenOrderService;

    public IndexController(KitchenOrderService kitchenOrderService) {
        this.kitchenOrderService = kitchenOrderService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("order", new KitchenOrderDto());
        List<String> listOfGroups = kitchenOrderService.findAll();
        model.addAttribute("listOfGroups", listOfGroups);
        return "order/new_order";

    }
}
