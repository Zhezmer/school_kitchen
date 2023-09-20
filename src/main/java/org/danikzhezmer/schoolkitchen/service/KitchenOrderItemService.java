package org.danikzhezmer.schoolkitchen.service;


import org.danikzhezmer.schoolkitchen.entity.KitchenOrderItem;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class KitchenOrderItemService {


    private final KitchenOrderItemRepository kitchenOrderItemRepository;

    public KitchenOrderItemService(KitchenOrderItemRepository kitchenOrderItemRepository) {
        this.kitchenOrderItemRepository = kitchenOrderItemRepository;
    }

    public List<KitchenOrderItem> findAllByKitchenOrderId(Long orderId){
        return kitchenOrderItemRepository.findAllByKitchenOrderId(orderId);
    }
}
