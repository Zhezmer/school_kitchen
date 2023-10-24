package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




@Controller
@RequestMapping("/products")
public class ProductController {

   private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") Long id, Model model) {

        model.addAttribute("product", productService.findById(id));
        return "/product/product_card";
    }

    @GetMapping
    public String getProductList(Model model) {
        model.addAttribute("products", productService.findAll());
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
        productService.save(product);
        return "redirect:/products";
    }
    @GetMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteById(productId);
        return "redirect:/products";
    }

}
