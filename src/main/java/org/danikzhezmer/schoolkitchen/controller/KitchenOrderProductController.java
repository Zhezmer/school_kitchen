package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.dto.KitchenOrderProductDto;
import org.danikzhezmer.schoolkitchen.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/kitchen_order_products")
public class KitchenOrderProductController {
private final ProductService productService;

    public KitchenOrderProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/new_kitchen_order")
    public String newKitchenOrderProductForm(Model model) {
        model.addAttribute("kitchen_order", new KitchenOrderProductDto());
        List<String> listOfProducts = Arrays.asList("milk", "bread", "shoko");
        model.addAttribute("listOfProducts", listOfProducts);
        List<String> listOfMeasure = Arrays.asList("kg", "pieces", "liter");
        model.addAttribute("listOfMeasure", listOfMeasure);

        return "/new_kitchen_order_product";
    }

    @PostMapping("/new_kitchen_order")
    public String submitForm(@ModelAttribute KitchenOrderProductDto kitchenOrder) {
       productService.save(kitchenOrder);
        return "redirect:/orders/new_order";
    }
}
