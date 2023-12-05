package org.danikzhezmer.schoolkitchen.service;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.danikzhezmer.schoolkitchen.entity.Statistic;
import org.danikzhezmer.schoolkitchen.repository.StatisticDAO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    private final StatisticDAO statisticDAO;


    public StatisticService(StatisticDAO statisticDAO) {
        this.statisticDAO = statisticDAO;
    }

    public List<Statistic> getStatistics(String groupName, LocalDate dateFrom, LocalDate dateTo, List<String> productNames) {


        return statisticDAO.getStat(groupName, dateFrom, dateTo, productNames);
    }


    public String generateAndSaveExcel() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = currentDate.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate startDate = currentDate.with(TemporalAdjusters.firstDayOfMonth());;

        List<Statistic> allStatistics = statisticDAO.getStatForAllGroupsAndProducts(startDate, endDate);

        XSSFWorkbook workbook = new XSSFWorkbook();

        Map<String, List<Statistic>> statisticsByGroup = allStatistics.stream()
                .collect(Collectors.groupingBy(Statistic::getGroupName));

        statisticsByGroup.forEach((groupName, groupStatistics) -> {

            XSSFSheet sheet = workbook.createSheet(groupName);


            Row dateRow = sheet.createRow(0);
            dateRow.createCell(0).setCellValue("From");
            dateRow.createCell(1).setCellValue(startDate.format(DateTimeFormatter.ISO_DATE));
            dateRow.createCell(2).setCellValue("Till");
            dateRow.createCell(3).setCellValue(endDate.format(DateTimeFormatter.ISO_DATE));


            Row headerRow = sheet.createRow(1);
            headerRow.createCell(0).setCellValue("Product Name");
            headerRow.createCell(1).setCellValue("Total Orders");
            headerRow.createCell(2).setCellValue("Measure");
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);

            int rowNum = 2;
            for (Statistic statistic : groupStatistics) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(statistic.getProduct());
                dataRow.createCell(1).setCellValue(statistic.getTotalQuantity());
                dataRow.createCell(2).setCellValue(statistic.getMeasure());
            }
        });

        // Save the workbook to a file
        String fileName = "Statistics_" + LocalDate.now() + ".xlsx";
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while generating file");
        }

        return fileName;
    }
}


