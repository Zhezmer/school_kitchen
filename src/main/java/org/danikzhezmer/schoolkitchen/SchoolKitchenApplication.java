package org.danikzhezmer.schoolkitchen;

import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrderItem;
import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
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
//        Product avocado = product("milk");
//        Product tomato = product("seledka");
//
//        KitchenOrder kitchenOrder = new KitchenOrder();
//        kitchenOrder.setCreationDate(LocalDate.now());
//        kitchenOrder.setOrderDateTo(LocalDate.now());
//        kitchenOrder.setGroup(schoolGroupRepository.findByName("test group"));
//        KitchenOrder savedOrder = kitchenOrderRepository.save(kitchenOrder);
//
//
//        addProductToOrder(savedOrder, avocado);
//        addProductToOrder(savedOrder, tomato);
//
//        KitchenOrder savedOrderWithItems = kitchenOrderRepository.findById(savedOrder.getId())
//                .orElse(null);
//        System.out.println(savedOrderWithItems);
//        System.out.println(savedOrderWithItems.getKitchenOrderItems());
    }
    private void addProductToOrder(KitchenOrder order, Product product) {
        KitchenOrderItem kitchenOrderProduct = new KitchenOrderItem();
        kitchenOrderProduct.setProduct(product);
        kitchenOrderProduct.setKitchenOrder(order);
        kitchenOrderProduct.setMeasure("шт");
        kitchenOrderProduct.setQty(10);
        kitchenOrderItemRepository.save(kitchenOrderProduct);
    }

    private Product product(String name) {
        return productRepository.save(new Product(null, name));
    }

    private SchoolGroup createGroup() {
        return schoolGroupRepository.save(new SchoolGroup(null, "test group"));
    }
}
