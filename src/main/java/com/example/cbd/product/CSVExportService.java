package com.example.cbd.product;

import com.example.cbd.storage.DeliveryInfoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class CSVExportService {
    
    private  static final Logger log = LoggerFactory.getLogger(CSVExportService.class);

    private final ProductRepository productRepository;

    private final DeliveryInfoRepository deliveryInfoRepository;

    private final CalculatorService calculatorService;

    public CSVExportService(ProductRepository productRepository, DeliveryInfoRepository deliveryInfoRepository, CalculatorService calculatorService) {
        this.productRepository = productRepository;
        this.deliveryInfoRepository = deliveryInfoRepository;
        this.calculatorService = calculatorService;
    }

    public void writeToCSV(Writer writer) {
        List<Product> products = productRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("id", "name", "description", "price", "location", "brand", "type", "material", "upper", "outsole", "insole", "delivery_time", "amount", "vat");
            for (Product product : products) {
                csvPrinter.printRecord(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getLocation(), product.getBrand(),
                        product.getType(), product.getMaterial(), product.getUpper(), product.getOutsole(), product.getInsole(),
                        deliveryInfoRepository.findById(product.getId()).get().getDeliveryTime(), deliveryInfoRepository.findById(product.getId()).get().getAmount(),
                        calculatorService.calcVat(product.getId()));
            }
        } catch (IOException e) {
            log.error("Error while writing CSV");
        }
    }



}
