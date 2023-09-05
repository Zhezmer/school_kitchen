package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.danikzhezmer.schoolkitchen.service.KitchenOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class KitchenOrderController {


    private final KitchenOrderService kitchenOrderService;

    private final SchoolGroupRepository schoolGroupRepository;

    public KitchenOrderController(KitchenOrderService kitchenOrderService, SchoolGroupRepository schoolGroupRepository) {

        this.kitchenOrderService = kitchenOrderService;
        this.schoolGroupRepository = schoolGroupRepository;
    }

    @GetMapping("/new_order")
    public String newOrderForm(Model model) {
        model.addAttribute("order", new KitchenOrderDto());
        List<String> listOfGroups = schoolGroupRepository.findAll()
                .stream()
                .map(SchoolGroup::getName)
                .collect(Collectors.toList());
        model.addAttribute("listOfGroups", listOfGroups);
        return "order/new_order";
    }

    @PostMapping("/new_order")
    public String submitForm(@ModelAttribute KitchenOrderDto order) {
        kitchenOrderService.save(order);
        return "redirect:/orders/new_order";
    }
}
