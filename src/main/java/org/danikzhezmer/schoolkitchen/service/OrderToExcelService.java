package org.danikzhezmer.schoolkitchen.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrderItem;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderItemRepository;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.FileOutputStream;
import java.time.Instant;
import java.util.List;


@Service
public class OrderToExcelService {
    private static final Logger log =
            LoggerFactory.getLogger(OrderToExcelService.class);

    private final KitchenOrderItemRepository kitchenOrderItemRepository;

    public OrderToExcelService(KitchenOrderItemRepository kitchenOrderItemRepository) {
        this.kitchenOrderItemRepository = kitchenOrderItemRepository;
    }

    public String generate(KitchenOrder order) {
        List<KitchenOrderItem> orderItems = kitchenOrderItemRepository.findAllByKitchenOrderId(order.getId());
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Order " + order.getId());

        Row row = sheet.createRow(0);
        Cell groupName = row.createCell(0);
        assert order != null;
        groupName.setCellValue(order.getGroup().getName());

        Row row1 = sheet.createRow(1);
        Cell cellNameCreation = row1.createCell(0);
        cellNameCreation.setCellValue("Creation date:");
        Cell creationDate = row1.createCell(1);
        DataFormat format = workbook.createDataFormat();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        creationDate.setCellStyle(dateStyle);
        creationDate.setCellValue(order.getCreationDate());
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        Cell cellNameExecute = row1.createCell(2);
        cellNameExecute.setCellValue("To execute:");
        Cell orderDateTo = row1.createCell(3);
        orderDateTo.setCellStyle(dateStyle);
        orderDateTo.setCellValue(order.getOrderDateTo());
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);

        Row headerRow = sheet.createRow(2);
        headerRow.createCell(0).setCellValue("Product Name");
        sheet.autoSizeColumn(0);
        headerRow.createCell(1).setCellValue("Measure");
        headerRow.createCell(2).setCellValue("Quantity");

        int rowNum = 3;
        for (KitchenOrderItem orderItem : orderItems) {
            Row orderRow = sheet.createRow(rowNum++);
            orderRow.createCell(0).setCellValue(orderItem.getProduct().getName());
            orderRow.createCell(1).setCellValue(orderItem.getMeasure());
            orderRow.createCell(2).setCellValue(orderItem.getQty());
        }
        String fileName = System.getProperty("java.io.tmpdir") + groupName + "`s order " + order.getId() + Instant.now().getNano() + ".xlsx";
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            workbook.write(out);
            log.info("document written successfully on disk. Filename: {}", fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while generating file");
        }
        return fileName;
    }
}
