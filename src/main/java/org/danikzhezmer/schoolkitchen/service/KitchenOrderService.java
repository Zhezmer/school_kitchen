package org.danikzhezmer.schoolkitchen.service;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderDto;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class KitchenOrderService {
    private final KitchenOrderRepository kitchenOrderRepository;
    private final SchoolGroupRepository schoolGroupRepository;

    public KitchenOrderService(KitchenOrderRepository kitchenOrderRepository, SchoolGroupRepository schoolGroupRepository) {
        this.kitchenOrderRepository = kitchenOrderRepository;
        this.schoolGroupRepository = schoolGroupRepository;
    }

    public void save(KitchenOrderDto kitchenOrderDto){
        SchoolGroup group = schoolGroupRepository.findByName(kitchenOrderDto.getGroupName());
        KitchenOrder kitchenOrder = new KitchenOrder();
        kitchenOrder.setCreationDate(kitchenOrderDto.getCreationDate());
        kitchenOrder.setOrderDateTo(kitchenOrderDto.getOrderDateTo());
        kitchenOrder.setGroup(group);

        kitchenOrderRepository.save(kitchenOrder);
    }
}
