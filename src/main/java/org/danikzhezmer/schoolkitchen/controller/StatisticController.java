package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.entity.Statistic;
import org.danikzhezmer.schoolkitchen.service.ProductService;
import org.danikzhezmer.schoolkitchen.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/statistic")
public class StatisticController {
    private final StatisticService statisticService;


    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
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
        List<Statistic> statistics = statisticService.getStatistics(groupName, localDateFrom, localDateTo, productNames);
        model.addAttribute("statistics", statistics);


        return "statistic/statistic_page";
    }
}
