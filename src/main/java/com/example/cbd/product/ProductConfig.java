package com.example.cbd.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository) {
        return args -> {
            Product ghost = new Product(
                    "Ghost",
                    "free",
                    129.95,
                    "here",
                    "Brooks",
                    "Sneakers",
                    "Mesh",
                    "Mesh",
                    "Rubber",
                    "Bio Mogo DNA Contoured"
            );

            Product edward = new Product(
                    "Edward",
                    "comfy",
                    110.0,
                    "the Void",
                    "Rockport",
                    "Slip-ons",
                    "Leather",
                    "Leather",
                    "Rubber",
                    "EVA"
            );

            repository.saveAll(
                    List.of(ghost, edward)
            );
        };

    }
}
