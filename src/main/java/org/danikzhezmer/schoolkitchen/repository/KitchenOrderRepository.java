package org.danikzhezmer.schoolkitchen.repository;

import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenOrderRepository  extends JpaRepository<KitchenOrder, Long> {

    KitchenOrder findKitchenOrderById(Long id);
}
