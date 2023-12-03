package org.danikzhezmer.schoolkitchen;


import org.danikzhezmer.schoolkitchen.repository.KitchenOrderItemRepository;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.danikzhezmer.schoolkitchen.repository.ProductRepository;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
@EnableScheduling
@SpringBootApplication
public class SchoolKitchenApplication {


    public static void main(String[] args) {
        SpringApplication.run(SchoolKitchenApplication.class, args);
    }
}


