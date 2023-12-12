package org.danikzhezmer.schoolkitchen.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.Statistic;
import org.danikzhezmer.schoolkitchen.repository.StatisticDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderMailSenderService {
    private static final Logger log =
            LoggerFactory.getLogger(OrderMailSenderService.class);

    private final JavaMailSender emailSender;
    private final OrderToExcelService orderToExcelService;
    private final KitchenOrderService kitchenOrderService;
    private final StatisticDAO statisticDAO;
    private final StatisticService statisticService;

    public OrderMailSenderService(JavaMailSender emailSender, OrderToExcelService orderToExcelService, KitchenOrderService kitchenOrderService, StatisticDAO statisticDAO, StatisticService statisticService) {
        this.emailSender = emailSender;
        this.orderToExcelService = orderToExcelService;

        this.kitchenOrderService = kitchenOrderService;
        this.statisticDAO = statisticDAO;
        this.statisticService = statisticService;
    }

    public void send(Long orderId) throws MessagingException {
        KitchenOrder order = kitchenOrderService.findById(orderId);
        String filename = orderToExcelService.generate(order);
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("schoolapp.mevoot@gmail.com");
        helper.setTo("giladmental@mevoot-eron.com");
        helper.setSubject("order");
        helper.setText("some text");

        File orderFile = new File(filename);
        FileSystemResource file
                = new FileSystemResource(orderFile);
        helper.addAttachment(orderFile.getName(), file);
        emailSender.send(message);
        kitchenOrderService.markAsSent(order);
        log.info("email send");
    }

    @Scheduled(cron = "0 0 20 * * SUN-THU")
    public void sendAll() throws MessagingException {
        List<KitchenOrder> all = kitchenOrderService.findNotSentOrders();
        if (all.isEmpty()) {
            log.info("No unsent orders to send");
            return;
        }
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("schoolapp.mevoot@gmail.com");
        helper.setTo("giladmental@mevoot-eron.com");
        helper.setSubject("Batch unsent orders");

        StringBuilder text = new StringBuilder("List of unsent orders:\n");

        for (KitchenOrder order : all) {
            String filename = orderToExcelService.generate(order);
            File orderFile = new File(filename);
            FileSystemResource file = new FileSystemResource(orderFile);
            helper.addAttachment(orderFile.getName(), file);
            text.append("Order ID: ").append(order.getId()).append("\n");
            kitchenOrderService.markAsSent(order);
        }

        helper.setText(text.toString());
        emailSender.send(message);
        log.info("Batch email send");

    }
    @Scheduled(cron = "0 59 23 L * ?")
    public void sendStatistics() throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("schoolapp.mevoot@gmail.com");
        helper.setTo("giladmental@mevoot-eron.com");
        helper.setSubject("Weekly Statistics Report");

        StringBuilder text = new StringBuilder("Weekly Statistics Report:\n");

        String filename = statisticService.generateAndSaveExcel();
        File statFile = new File(filename);
        FileSystemResource file = new FileSystemResource(statFile);
        helper.addAttachment(statFile.getName(), file);

        text.append("Attached statistics file for the last week.\n");

        helper.setText(text.toString());
        emailSender.send(message);
        log.info("Statistics email sent");
    }
}