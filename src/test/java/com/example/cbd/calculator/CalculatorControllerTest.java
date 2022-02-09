package com.example.cbd.calculator;

import com.example.cbd.product.CalculatorService;
import com.example.cbd.product.Product;
import com.example.cbd.product.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CalculatorControllerTest {

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    @InjectMocks
    private CalculatorController calculatorTest;
    private Product product1;
    private Product product2;
    List<Product> productList;

    @BeforeEach
    void setUp() {
        productList = new ArrayList<>();
        product1 = new Product(1L, "n", "d", 100, "l", "b", "t", "m", "u", "o", "i");
        product2 = new Product(2L, "n", "d", 14.12, "l", "b", "t", "m", "u", "o", "i");
        productList.add(product1);
        productList.add(product2);
    }

    @AfterEach
    public void tearDown() {
        product1 = product2 = null;
        productList = null;
    }

    @Test
    void calcVatTest() {
        when(calculatorService.calcVat(1L)).thenReturn(19.0);

        assertEquals(calculatorTest.calcVat(1L), 19);
    }

}