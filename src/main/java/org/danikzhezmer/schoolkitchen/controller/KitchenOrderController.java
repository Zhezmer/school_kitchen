package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/orders")
public class KitchenOrderController {

    KitchenOrderRepository kitchenOrderRepository;

    public KitchenOrderController(KitchenOrderRepository kitchenOrderRepository) {
        this.kitchenOrderRepository = kitchenOrderRepository;
    }

    @GetMapping("/new_order")
    public String newOrderForm(Model model ) {
        model.addAttribute("order", new KitchenOrder());
        model.addAttribute("standardDate", new Date());

        return "new_order";
    }

    @PostMapping("/new_order")
    public String submitForm(@ModelAttribute KitchenOrder order, Model model) {
        model.addAttribute("new_order", order);
        kitchenOrderRepository.saveAndFlush(order);
        return "new_order";
    }
}
