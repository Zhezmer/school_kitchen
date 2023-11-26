package org.danikzhezmer.schoolkitchen.service;


import jakarta.transaction.Transactional;
import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrderItem;
import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;

import org.danikzhezmer.schoolkitchen.repository.KitchenOrderItemRepository;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.danikzhezmer.schoolkitchen.repository.ProductRepository;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;


@Service
public class KitchenOrderService {
    private final KitchenOrderRepository kitchenOrderRepository;
    private final KitchenOrderItemRepository kitchenOrderItemRepository;
    private final SchoolGroupRepository schoolGroupRepository;

    private final ProductRepository productRepository;

    public KitchenOrderService(KitchenOrderRepository kitchenOrderRepository, KitchenOrderItemRepository kitchenOrderItemRepository, SchoolGroupRepository schoolGroupRepository, ProductRepository productRepository) {
        this.kitchenOrderRepository = kitchenOrderRepository;
        this.kitchenOrderItemRepository = kitchenOrderItemRepository;
        this.schoolGroupRepository = schoolGroupRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void save(KitchenOrderDto dto) {
        SchoolGroup group = schoolGroupRepository.findById(dto.getGroupId()).orElse(null);
        KitchenOrder kitchenOrder = dto.getOrderId() == null
                ? new KitchenOrder()
                : kitchenOrderRepository.findKitchenOrderById(dto.getOrderId());
        kitchenOrder.setId(dto.getOrderId());
        kitchenOrder.setCreationDate(LocalDate.now());
        kitchenOrder.setOrderDateTo(dto.getOrderDateTo());
        kitchenOrder.setGroup(group);
        kitchenOrder.setSent(false);

        KitchenOrder savedOrder = kitchenOrderRepository.save(kitchenOrder);

        List<KitchenOrderItem> kitchenOrderItems = dto.getItems()
                .stream()
                .map(item -> {
                    Product product = productRepository.findById(item.getProductId()).orElse(null);
                    KitchenOrderItem kitchenOrderItem = new KitchenOrderItem();
                    kitchenOrderItem.setProduct(product);
                    kitchenOrderItem.setKitchenOrder(savedOrder);
                    kitchenOrderItem.setMeasure(item.getMeasure());
                    kitchenOrderItem.setQty(item.getQty());
                    return kitchenOrderItem;
                }).toList();
        kitchenOrderItemRepository.deleteAllByKitchenOrderId(savedOrder.getId());
        kitchenOrderItemRepository.saveAll(kitchenOrderItems);


    }

    public List<KitchenOrder> findAll() {
        return kitchenOrderRepository.findAll();
    }

    public KitchenOrder findById(Long id) {
        return kitchenOrderRepository.findById(id).orElse(null);
    }

    public List<KitchenOrder> findKitchenOrderByGroupName(String groupName) {
        return kitchenOrderRepository.findKitchenOrderByGroupName(groupName);
    }
    @Transactional
    public void deleteKitchenOrderById(Long id) {
        kitchenOrderItemRepository.deleteAllByKitchenOrderId(id);
        kitchenOrderRepository.deleteById(id);
    }

    public void markAsSent(KitchenOrder order){
        order.setSent(true);
        kitchenOrderRepository.save(order);
    }

    public List<KitchenOrder> findNotSendedOrders(){
        return kitchenOrderRepository.findAllByIsSent(false);
    }

}