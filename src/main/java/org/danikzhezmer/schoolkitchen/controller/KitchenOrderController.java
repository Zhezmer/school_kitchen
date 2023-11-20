package org.danikzhezmer.schoolkitchen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.dto.KitchenOrderItemDto;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrderItem;
import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/orders")
public class KitchenOrderController {



    private final KitchenOrderService kitchenOrderService;
    private final KitchenOrderItemService kitchenOrderItemService;
    private final SchoolGroupService schoolGroupService;

    private final OrderMailSenderService orderMailSenderService;

    private final ProductService productService;

    public KitchenOrderController(KitchenOrderService kitchenOrderService, KitchenOrderItemService kitchenOrderItemService, SchoolGroupService schoolGroupService, OrderToExcelService orderToExcelService, OrderMailSenderService orderMailSenderService, ProductService productService) {
        this.kitchenOrderService = kitchenOrderService;
        this.kitchenOrderItemService = kitchenOrderItemService;
        this.schoolGroupService = schoolGroupService;
        this.orderMailSenderService = orderMailSenderService;
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", kitchenOrderService.findById(id));
        model.addAttribute("items", kitchenOrderItemService.findAllByKitchenOrderId(id));
        return "/order/order_card";
    }

    @GetMapping
    public String getOrderList(Model model) {
        model.addAttribute("groups", schoolGroupService.findAll());
        return "order/order_list";
    }

    @GetMapping("/groupOrders/{groupName}")
    public String getGroupOrders(@PathVariable("groupName") String groupName, Model model) {
        model.addAttribute("group_orders", kitchenOrderService.findKitchenOrderByGroupName(groupName));
        return "order/group_orders";
    }

    @GetMapping("/new_order")
    public String newOrderForm(Model model) throws JsonProcessingException {
        model.addAttribute("order", new KitchenOrderDto());
        List<SchoolGroup> groups = schoolGroupService.findAll();
        List<Product> products = productService.findAll();

        model.addAttribute("groups", groups);
        model.addAttribute("products", products);
        return "order/new_order";
    }

    @GetMapping("/{id}/edit")
    public String editOrder(@PathVariable("id") Long id, Model model) {
        KitchenOrder kitchenOrder = kitchenOrderService.findById(id);
        List<KitchenOrderItem> kitchenOrderItems = kitchenOrderItemService.findAllByKitchenOrderId(id);

        KitchenOrderDto dto = new KitchenOrderDto();
        dto.setOrderId(kitchenOrder.getId());
        dto.setGroupId(kitchenOrder.getGroup().getId());
        dto.setOrderDateTo(kitchenOrder.getOrderDateTo());
        dto.setCreationDate(kitchenOrder.getCreationDate());
        dto.setItems(
                kitchenOrderItems.stream()
                        .map(kitchenOrderItem -> {
                            KitchenOrderItemDto item = new KitchenOrderItemDto();
                            item.setMeasure(kitchenOrderItem.getMeasure());
                            item.setProductId(kitchenOrderItem.getProduct().getId());
                            item.setQty(kitchenOrderItem.getQty());
                            return item;
                        }).toList()
        );

        List<SchoolGroup> groups = schoolGroupService.findAll();
        List<Product> products = productService.findAll();

        model.addAttribute("groups", groups);
        model.addAttribute("products", products);

        model.addAttribute("order", dto);
        return "/order/new_order";
    }

    @GetMapping ("/{id}/delete")
    public String deleteOrder(@PathVariable("id") Long id) {
        kitchenOrderService.deleteKitchenOrderById(id);
        return "redirect:/orders";
    }

    @PostMapping("/new_order")
    public String submitForm(@RequestBody KitchenOrderDto order) {
        kitchenOrderService.save(order);
        return "redirect:/kitchen_order_items/new_kitchen_order_item";
    }

    @GetMapping("{id}/xlsx")
    public String getExelFile(@PathVariable Long id) {
        try {
            orderMailSenderService.send(id);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "redirect:/orders";
    }


}