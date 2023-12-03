package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.entity.Statistic;
import org.danikzhezmer.schoolkitchen.service.ProductService;
import org.danikzhezmer.schoolkitchen.service.SchoolGroupService;
import org.danikzhezmer.schoolkitchen.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/statistic")
public class StatisticController {
    private final StatisticService statisticService;
    private final SchoolGroupService schoolGroupService;
    private final ProductService productService;

    public StatisticController(StatisticService statisticService, SchoolGroupService schoolGroupService, ProductService productService) {
        this.statisticService = statisticService;
        this.schoolGroupService = schoolGroupService;
        this.productService = productService;
    }

    @GetMapping
    public String showStatisticsPage() {
        return "statistic/statistic_page";
    }

    @GetMapping("/getStatistics")
    public String getStatistics(
            @RequestParam String groupName,
            @RequestParam String dateFrom,
            @RequestParam String dateTo,
            @RequestParam List<String> productNames,
            Model model
    ) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom);
        LocalDate localDateTo = LocalDate.parse(dateTo);
        List<String> products = productService.findAll().stream().map(Product::getName).collect(Collectors.toList());
        List<String> groups = schoolGroupService.findAll().stream().map(SchoolGroup::getName).collect(Collectors.toList());
        List<Statistic> statistics = statisticService.getStatistics(groupName, localDateFrom, localDateTo, productNames);

        model.addAttribute("statistics", statistics);
        model.addAttribute("products", products);
        model.addAttribute("groups", groups);

        return "statistic/statistic_page";
    }
}
