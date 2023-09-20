package org.danikzhezmer.schoolkitchen.service;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderItemDto;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrderItem;
import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderItemRepository;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.danikzhezmer.schoolkitchen.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KitchenOrderItemService {
    private final KitchenOrderRepository kitchenOrderRepository;

    private final ProductRepository productRepository;

    private final KitchenOrderItemRepository kitchenOrderItemRepository;

    public KitchenOrderItemService(KitchenOrderRepository kitchenOrderRepository, ProductRepository productRepository, KitchenOrderItemRepository kitchenOrderItemRepository) {
        this.kitchenOrderRepository = kitchenOrderRepository;
        this.productRepository = productRepository;
        this.kitchenOrderItemRepository = kitchenOrderItemRepository;
    }
    public List<KitchenOrderItem> findAll(){
        return kitchenOrderItemRepository.findAll();
    }

    public KitchenOrderItem findById(Long id){
        return kitchenOrderItemRepository.findById(id).orElse(null);
    }

    public void save(KitchenOrderItemDto kitchenOrderItemDto){
        Product product = productRepository.findById(kitchenOrderItemDto.getProductId()).orElseThrow();
        KitchenOrder kitchenOrder = kitchenOrderRepository.findKitchenOrderById(kitchenOrderItemDto.getKitchenOrderId());
        KitchenOrderItem kitchenOrderItem = new KitchenOrderItem();
        kitchenOrderItem.setKitchenOrder(kitchenOrder);
        kitchenOrderItem.setProduct(product);
        kitchenOrderItem.setMeasure(kitchenOrderItemDto.getMeasure());
        kitchenOrderItem.setQty(kitchenOrderItemDto.getQty());

        kitchenOrderItemRepository.save(kitchenOrderItem);
    }

    public List<String>  getAllProductNames(){
        return productRepository.findAll().stream().map(Product::getName).collect(Collectors.toList());
    }
    public List<KitchenOrderItem> findAllByKitchenOrderId(Long orderId){
        return kitchenOrderItemRepository.findAllByKitchenOrderId(orderId);
    }
}
