package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/products")
public class ProductController {

   private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/new_product")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/new_product";
    }

    @PostMapping("/new_product")
    public String submitForm(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        productRepository.save(product);
        return "redirect:/orders/new_order";
    }
}
