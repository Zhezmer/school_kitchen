package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {

   private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public String getproduct(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "/product/product_card";
    }

    @GetMapping
    public String getProductList(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product/product_list";
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
        return "redirect:/products";
    }
}
