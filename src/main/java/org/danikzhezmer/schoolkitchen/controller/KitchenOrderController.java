package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class KitchenOrderController {

    KitchenOrderRepository kitchenOrderRepository;
    SchoolGroupRepository schoolGroupRepository;

    public KitchenOrderController(KitchenOrderRepository kitchenOrderRepository) {
        this.kitchenOrderRepository = kitchenOrderRepository;
    }

    @GetMapping("/new_order")
    public String newOrderForm(Model model ) {
        model.addAttribute("order", new KitchenOrderDto());
        List<String> listOfGroups = Arrays.asList("Group1", "Group2", "Group3");
        model.addAttribute("listOfGroups", listOfGroups);
        return "new_order";
    }

    @PostMapping("/new_order")
    public String submitForm(@ModelAttribute KitchenOrderDto order, Model model) {
        model.addAttribute("order", order);
      // kitchenOrderRepository.saveAndFlush(order);
        return "new_order";
    }
}
