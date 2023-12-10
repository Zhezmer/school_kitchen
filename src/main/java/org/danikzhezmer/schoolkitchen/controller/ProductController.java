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

        Product existingProduct = productService.findByName(product.getName());

        if (existingProduct == null) {
            productService.save(product);
        } else {
            existingProduct.setInStock(true);
            productService.save(existingProduct);
        }

        return "redirect:/products";
    }

    @GetMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        productService.markOutOfStock(product);
        return "redirect:/products";
    }

}
