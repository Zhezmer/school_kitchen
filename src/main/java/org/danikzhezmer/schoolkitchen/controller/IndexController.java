package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.service.KitchenOrderService;
import org.danikzhezmer.schoolkitchen.service.SchoolGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {
   private final SchoolGroupService schoolGroupService;

    public IndexController(SchoolGroupService schoolGroupService) {
        this.schoolGroupService = schoolGroupService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("order", new KitchenOrderDto());
        List<String> listOfGroups = schoolGroupService.findAll().stream().map(SchoolGroup::getName).collect(Collectors.toList());
        model.addAttribute("listOfGroups", listOfGroups);
        return "order/new_order";

    }
}
