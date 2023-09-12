package org.danikzhezmer.schoolkitchen.controller;


import org.danikzhezmer.schoolkitchen.dto.KitchenOrderItemDto;
import org.danikzhezmer.schoolkitchen.service.KitchenOrderItemService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/kitchen_order_items")
public class KitchenOrderItemController {
private final KitchenOrderItemService kitchenOrderItemService;

    public KitchenOrderItemController(KitchenOrderItemService kitchenOrderItemService) {
        this.kitchenOrderItemService = kitchenOrderItemService;
    }

    @GetMapping("/{id}")
    public String getKitchenOrderItem(@PathVariable("id") Long id, Model model) {
        model.addAttribute("kitchen_order_item", kitchenOrderItemService.findById(id));
        return "/kitchen_order_item/kitchen_order_item_card";
    }

    @GetMapping
    public String getKitchenOrderItemList(Model model) {
        model.addAttribute("kitchen_order_items", kitchenOrderItemService.findAll());
        return "kitchen_order_item/kitchen_order_item_list";
    }

    @GetMapping("/new_kitchen_order_item")
    public String newKitchenOrderItemForm(Model model) {
        model.addAttribute("kitchen_order_item", new KitchenOrderItemDto());
        model.addAttribute("listOfProducts", kitchenOrderItemService.findProducts());
        List<String> listOfMeasure = Arrays.asList("kg", "pieces", "liter");
        model.addAttribute("listOfMeasure", listOfMeasure);

        return "/kitchen_order_item/new_kitchen_order_item";
    }

    @PostMapping("/new_kitchen_order_item")
    public String submitForm(@ModelAttribute KitchenOrderItemDto kitchenOrder) {
       kitchenOrderItemService.save(kitchenOrder);
        return "redirect:/orders/new_order";
    }

}
