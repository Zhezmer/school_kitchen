package org.danikzhezmer.schoolkitchen.repository;

import org.danikzhezmer.schoolkitchen.entity.KitchenOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KitchenOrderItemRepository extends JpaRepository<KitchenOrderItem, Long> {


    List<KitchenOrderItem> findAllByKitchenOrderId(Long kitchenOrderId);
}
