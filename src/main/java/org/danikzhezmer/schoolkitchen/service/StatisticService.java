package org.danikzhezmer.schoolkitchen.service;


import org.danikzhezmer.schoolkitchen.entity.Statistic;
import org.danikzhezmer.schoolkitchen.repository.StatisticDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticService  {

    private final StatisticDAO statisticDAO;


    public StatisticService(StatisticDAO statisticDAO) {
        this.statisticDAO = statisticDAO;
    }

    public List<Statistic> getStatistics(String groupName,
                                         LocalDate dateFrom,
                                         LocalDate dateTo,
                                         List<String> productNames) {

        return statisticDAO.getStat(groupName, dateFrom, dateTo, productNames);
    }
}
