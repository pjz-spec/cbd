package com.example.cbd.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private final ProductRepository productRepository;

    @Autowired
    public CalculatorService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public double calcVat(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("product with id " + productId + " does not exist"));
        return Math.round(product.getPrice() * 0.19 * 100.0) / 100.0;
    }
}
