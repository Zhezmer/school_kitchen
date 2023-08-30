package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.danikzhezmer.schoolkitchen.service.KitchenOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class KitchenOrderController {


    private final KitchenOrderService kitchenOrderService;

    public KitchenOrderController(KitchenOrderService kitchenOrderService) {

        this.kitchenOrderService = kitchenOrderService;
    }

    @GetMapping("/new_order")
    public String newOrderForm(Model model) {
        model.addAttribute("order", new KitchenOrderDto());
        List<String> listOfGroups = Arrays.asList("Group1", "Group2", "Group3");
        model.addAttribute("listOfGroups", listOfGroups);
        return "order/new_order";
    }

    @PostMapping("/new_order")
    public String submitForm(@ModelAttribute KitchenOrderDto order) {
        kitchenOrderService.save(order);
        return "redirect:/orders/new_order";
    }
}
