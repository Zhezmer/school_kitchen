package org.danikzhezmer.schoolkitchen;

import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SchoolKitchenApplication implements CommandLineRunner {

    @Autowired
    private KitchenOrderRepository kitchenOrderRepository;

    @Autowired
    private SchoolGroupRepository schoolGroupRepository;

    public static void main(String[] args) {
        SpringApplication.run(SchoolKitchenApplication.class, args);
    }
    @Transactional
    @Override
    public void run(String... args) throws Exception {
//        SchoolGroup group1 = new SchoolGroup(null, "group1");
//        SchoolGroup group2 = new SchoolGroup(null, "group2");
//
//        SchoolGroup saved_group = schoolGroupRepository.save(group1);
//        schoolGroupRepository.save(group2);
//
//        KitchenOrder order = new KitchenOrder();
//        order.setGroup(saved_group);
//        order.setStatus("in process");
//        order.setCreationDate(Instant.now());
//        order.setOrderDateTo(Instant.now());
//
//        kitchenOrderRepository.save(order);
    }
}
