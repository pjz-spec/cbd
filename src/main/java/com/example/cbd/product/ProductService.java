package com.example.cbd.product;

import com.example.cbd.storage.DeliveryInfo;
import com.example.cbd.storage.DeliveryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final DeliveryInfoService deliveryInfoService;

    @Autowired
    public ProductService(ProductRepository productRepository, DeliveryInfoService deliveryInfoService) {
        this.productRepository = productRepository;
        this.deliveryInfoService = deliveryInfoService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException();
        }
        return productRepository.findById(productId).get();
    }

    public void addNewProduct(Product product) {
        /* Optional<Product> productOptional = productRepository.findProductByBrand(product.getBrand());
        if (productOptional.isPresent()) {
            throw new IllegalStateException("brand taken");
        }*/
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException();
        }
        productRepository.findById(productId);
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Transactional
    public void updateProduct(Long productId, String name) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("product with id " + productId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(product.getName(), name)) {
            product.setName(name);
        }
    }

    public void storeAllProducts() {
        DeliveryInfo info;
        for (int i = 0; i < 3; i++) {
            info = new DeliveryInfo(5, 100, "here");
            deliveryInfoService.addNewDeliveryInfo(info);
        }
        System.out.println(deliveryInfoService.getDeliveryInfo(3L).getAmount());
    }
}
