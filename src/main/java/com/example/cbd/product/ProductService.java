package com.example.cbd.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
//import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

    @Transactional
    public void updateProduct(Long productId, String name) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("product with id " + productId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(product.getName(), name)) {
            product.setName(name);
        }
    }
}
