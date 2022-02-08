package com.example.cbd.product;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.cbd.product",
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef= "productTransactionManager")
public class ProductConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.product")
    public DataSourceProperties productDatasourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.product.configuration")
    public DataSource productDataSource() {
        return productDatasourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }
    @Primary
    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(productDataSource())
                .packages(Product.class)
                .build();
    }
    @Primary
    @Bean
    public PlatformTransactionManager productTransactionManager(
            final @Qualifier("productEntityManagerFactory") LocalContainerEntityManagerFactoryBean productEntityManagerFactory) {
        return new JpaTransactionManager(productEntityManagerFactory.getObject());
    }

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
