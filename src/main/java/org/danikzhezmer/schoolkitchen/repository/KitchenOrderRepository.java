package org.danikzhezmer.schoolkitchen.repository;

import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KitchenOrderRepository  extends JpaRepository<KitchenOrder, Long> {

    KitchenOrder findKitchenOrderById(Long id);
    List<KitchenOrder> findKitchenOrderByGroupName(String groupName);

    List<KitchenOrder> findByGroupNameAndCreationDateBetween(String groupName, LocalDate startDate, LocalDate endDate);


    List<KitchenOrder> findAllByIsSent(Boolean isSent);






}
