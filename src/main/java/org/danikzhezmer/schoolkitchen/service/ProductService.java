package org.danikzhezmer.schoolkitchen.service;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderProductDto;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrderProduct;
import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderProductRepository;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.danikzhezmer.schoolkitchen.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final KitchenOrderRepository kitchenOrderRepository;

    private final ProductRepository productRepository;

    private final KitchenOrderProductRepository kitchenOrderProductRepository;

    public ProductService(KitchenOrderRepository kitchenOrderRepository, ProductRepository productRepository, KitchenOrderProductRepository kitchenOrderProductRepository) {
        this.kitchenOrderRepository = kitchenOrderRepository;
        this.productRepository = productRepository;
        this.kitchenOrderProductRepository = kitchenOrderProductRepository;
    }

    public void save(KitchenOrderProductDto kitchenOrderProductDto){
        Product product = productRepository.findByProductId(kitchenOrderProductDto.getProductId());
        KitchenOrder kitchenOrder = kitchenOrderRepository.findKitchenOrderById(kitchenOrderProductDto.getKitchenOrderId());
        KitchenOrderProduct kitchenOrderProduct = new KitchenOrderProduct();
        kitchenOrderProduct.setKitchenOrder(kitchenOrder);
        kitchenOrderProduct.setProduct(product);
        kitchenOrderProduct.setMeasure(kitchenOrderProductDto.getMeasure());
        kitchenOrderProduct.setQty(kitchenOrderProductDto.getQty());

        kitchenOrderProductRepository.save(kitchenOrderProduct);
    }
}
