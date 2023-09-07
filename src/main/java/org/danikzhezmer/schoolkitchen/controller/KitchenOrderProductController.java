package org.danikzhezmer.schoolkitchen.controller;


import org.danikzhezmer.schoolkitchen.dto.KitchenOrderProductDto;
import org.danikzhezmer.schoolkitchen.service.KitchenOrderProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/kitchen_order_products")
public class KitchenOrderProductController {
private final KitchenOrderProductService kitchenOrderProductService;

    public KitchenOrderProductController(KitchenOrderProductService kitchenOrderProductService) {
        this.kitchenOrderProductService = kitchenOrderProductService;
    }

    @GetMapping("/{id}")
    public String getKitchenOrderProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("kitchen_order_product", kitchenOrderProductService.findById(id));
        return "/kitchen_order_product/kitchen_order_product_card";
    }

    @GetMapping
    public String getKitchenOrderProductList(Model model) {
        model.addAttribute("kitchen_order_products", kitchenOrderProductService.findAll());

        return "kitchen_order_product/kitchen_order_product_list";
    }

    @GetMapping("/new_kitchen_order_product")
    public String newKitchenOrderProductForm(Model model) {
        model.addAttribute("kitchen_order", new KitchenOrderProductDto());
        model.addAttribute("listOfProducts", kitchenOrderProductService.findProducts());
        List<String> listOfMeasure = Arrays.asList("kg", "pieces", "liter");
        model.addAttribute("listOfMeasure", listOfMeasure);

        return "/kitchen_order_product/new_kitchen_order_product";
    }

    @PostMapping("/new_kitchen_order_product")
    public String submitForm(@ModelAttribute KitchenOrderProductDto kitchenOrder) {
       kitchenOrderProductService.save(kitchenOrder);
        return "redirect:/orders/new_order";
    }

}
