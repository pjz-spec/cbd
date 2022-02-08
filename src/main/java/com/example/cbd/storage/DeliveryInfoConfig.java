package com.example.cbd.storage;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.cbd.storage",
        entityManagerFactoryRef = "deliveryInfoEntityManagerFactory",
        transactionManagerRef= "deliveryInfoTransactionManager")
public class DeliveryInfoConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.deliveryinfo")
    public DataSourceProperties deliveryInfoDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.deliveryinfo.configuration")
    public DataSource deliveryInfoDataSource() {
        return deliveryInfoDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }
    @Bean(name = "deliveryInfoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean deliveryInfoEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(deliveryInfoDataSource())
                .packages(DeliveryInfo.class)
                .build();
    }
    @Bean
    public PlatformTransactionManager deliveryInfoTransactionManager(
            final @Qualifier("deliveryInfoEntityManagerFactory") LocalContainerEntityManagerFactoryBean deliveryInfoEntityManagerFactory) {
        return new JpaTransactionManager(deliveryInfoEntityManagerFactory.getObject());
    }

}
