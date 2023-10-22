package org.danikzhezmer.schoolkitchen;


import org.danikzhezmer.schoolkitchen.repository.KitchenOrderItemRepository;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.danikzhezmer.schoolkitchen.repository.ProductRepository;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootApplication
public class SchoolKitchenApplication implements CommandLineRunner {

    @Autowired
    private KitchenOrderRepository kitchenOrderRepository;

    @Autowired
    private SchoolGroupRepository schoolGroupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KitchenOrderItemRepository kitchenOrderItemRepository;
    public static void main(String[] args) {
        SpringApplication.run(SchoolKitchenApplication.class, args);
    }
    @Transactional
    @Override
    public void run(String... args) throws Exception {

    }


    }
