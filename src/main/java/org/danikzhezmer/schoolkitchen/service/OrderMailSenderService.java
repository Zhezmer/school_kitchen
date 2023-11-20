package org.danikzhezmer.schoolkitchen.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OrderMailSenderService {

    private final JavaMailSender emailSender;
    private final OrderToExcelService orderToExcelService;

    private final SchoolGroupService schoolGroupService;

    public OrderMailSenderService(JavaMailSender emailSender, OrderToExcelService orderToExcelService, SchoolGroupService schoolGroupService) {
        this.emailSender = emailSender;
        this.orderToExcelService = orderToExcelService;
        this.schoolGroupService = schoolGroupService;
    }

    public void send(Long orderId) throws MessagingException {
      String filename = orderToExcelService.generate(orderId);
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("schoolapp.mevoot@gmail.com");
        helper.setTo("d.zhezmer@gmail.com");
        helper.setSubject(schoolGroupService.findById(orderId).getName() + " order");//TODO add groupName
        helper.setText("   ");

        File orderFile = new File(filename);
        FileSystemResource file
                = new FileSystemResource(orderFile);
        helper.addAttachment(orderFile.getName(), file);
        emailSender.send(message);
    }
}
