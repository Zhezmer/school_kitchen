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
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class OrderToExcelService {

    private final KitchenOrderRepository kitchenOrderRepository;
    private final KitchenOrderItemRepository kitchenOrderItemRepository;

    public OrderToExcelService(KitchenOrderRepository kitchenOrderRepository, KitchenOrderItemRepository kitchenOrderItemRepository) {
        this.kitchenOrderRepository = kitchenOrderRepository;
        this.kitchenOrderItemRepository = kitchenOrderItemRepository;
    }

    public void generate(Long orderId) {
        KitchenOrder order = kitchenOrderRepository.findById(orderId).orElse(null);
        List<KitchenOrderItem> orderItems = kitchenOrderItemRepository.findAllByKitchenOrderId(orderId);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Order " + orderId);

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
        try(FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + "/Desktop/yourFileName.xlsx" + orderId))) {
            workbook.write(out);
            System.out.println("document written successfully on disk.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }






    }
}
