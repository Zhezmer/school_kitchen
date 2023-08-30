package org.danikzhezmer.schoolkitchen.repository;

import org.danikzhezmer.schoolkitchen.entity.KitchenOrderProduct;
import org.danikzhezmer.schoolkitchen.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenOrderProductRepository extends JpaRepository<KitchenOrderProduct, Long> {


}
