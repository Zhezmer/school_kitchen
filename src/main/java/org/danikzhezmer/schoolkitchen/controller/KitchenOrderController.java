package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.service.KitchenOrderItemService;
import org.danikzhezmer.schoolkitchen.service.KitchenOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/orders")
public class KitchenOrderController {

    private final KitchenOrderService kitchenOrderService;
    private final KitchenOrderItemService kitchenOrderItemService;

    public KitchenOrderController(KitchenOrderService kitchenOrderService, KitchenOrderItemService kitchenOrderItemService) {
        this.kitchenOrderService = kitchenOrderService;
        this.kitchenOrderItemService = kitchenOrderItemService;
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", kitchenOrderService.findById(id));
        model.addAttribute("items", kitchenOrderItemService.findAllByKitchenOrderId(id));
        return "/order/order_card";
    }

    @GetMapping
    public String getOrderList(Model model) {
        model.addAttribute("orders", kitchenOrderService.findAll());
        return "order/order_list";
    }
    @GetMapping("/groupOrders/{groupName}")
    public String getGroupOrders(@PathVariable("groupName")String groupName, Model model){
        model.addAttribute("group_orders", kitchenOrderService.findKitchenOrderByGroupName(groupName));
        return "order/group_orders";
    }

    @GetMapping("/new_order")
    public String newOrderForm(Model model) {
        model.addAttribute("order", new KitchenOrderDto());
        List<String> listOfGroups = kitchenOrderService.findAll().stream().map(KitchenOrder::getGroup).map(SchoolGroup::getName).collect(Collectors.toList());
        model.addAttribute("listOfGroups", listOfGroups);
        return "order/new_order";
    }

    @PostMapping("/new_order")
    public String submitForm(@ModelAttribute KitchenOrderDto order) {
        kitchenOrderService.save(order);
        return "redirect:/kitchen_order_items/new_kitchen_order_item";
    }
}
