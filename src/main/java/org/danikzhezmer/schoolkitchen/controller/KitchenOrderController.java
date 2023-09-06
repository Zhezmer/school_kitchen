package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.service.KitchenOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/orders")
public class KitchenOrderController {

    private final KitchenOrderService kitchenOrderService;

    public KitchenOrderController(KitchenOrderService kitchenOrderService) {
        this.kitchenOrderService = kitchenOrderService;
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", kitchenOrderService.findById(id));
        return "/order/order_card";
    }

    @GetMapping
    public String getOrderList(Model model) {
        model.addAttribute("orders", kitchenOrderService.findAll());

        return "order/order_list";
    }

    @GetMapping("/new_order")
    public String newOrderForm(Model model) {
        model.addAttribute("order", new KitchenOrderDto());
        List<String> listOfGroups = kitchenOrderService.findAll();
        model.addAttribute("listOfGroups", listOfGroups);
        return "order/new_order";
    }

    @PostMapping("/new_order")
    public String submitForm(@ModelAttribute KitchenOrderDto order) {
        kitchenOrderService.save(order);
        return "redirect:/kitchen_order_products/new_kitchen_order_product";
    }
}
