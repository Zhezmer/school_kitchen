package org.danikzhezmer.schoolkitchen.service;

import org.danikzhezmer.schoolkitchen.dto.KitchenOrderProductDto;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrder;
import org.danikzhezmer.schoolkitchen.entity.KitchenOrderProduct;
import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderProductRepository;
import org.danikzhezmer.schoolkitchen.repository.KitchenOrderRepository;
import org.danikzhezmer.schoolkitchen.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KitchenOrderProductService {
    private final KitchenOrderRepository kitchenOrderRepository;

    private final ProductRepository productRepository;

    private final KitchenOrderProductRepository kitchenOrderProductRepository;

    public KitchenOrderProductService (KitchenOrderRepository kitchenOrderRepository, ProductRepository productRepository, KitchenOrderProductRepository kitchenOrderProductRepository) {
        this.kitchenOrderRepository = kitchenOrderRepository;
        this.productRepository = productRepository;
        this.kitchenOrderProductRepository = kitchenOrderProductRepository;
    }
    public List<KitchenOrderProduct> findAll(){
        return kitchenOrderProductRepository.findAll();
    }

    public KitchenOrderProduct findById(Long id){
        return kitchenOrderProductRepository.findById(id).orElse(null);
    }

    public void save(KitchenOrderProductDto kitchenOrderProductDto){
        Product product = productRepository.findById(kitchenOrderProductDto.getProductId()).orElseThrow();
        KitchenOrder kitchenOrder = kitchenOrderRepository.findKitchenOrderById(kitchenOrderProductDto.getKitchenOrderId());
        KitchenOrderProduct kitchenOrderProduct = new KitchenOrderProduct();
        kitchenOrderProduct.setKitchenOrder(kitchenOrder);
        kitchenOrderProduct.setProduct(product);
        kitchenOrderProduct.setMeasure(kitchenOrderProductDto.getMeasure());
        kitchenOrderProduct.setQty(kitchenOrderProductDto.getQty());

        kitchenOrderProductRepository.save(kitchenOrderProduct);
    }

    public List<String> findProducts(){
        return productRepository.findAll().stream().map(Product::getName).collect(Collectors.toList());
    }
}
