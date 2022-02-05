package com.example.cbd.product;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class CsvExportService {
    
    private  static final Logger log = LoggerFactory.getLogger(CsvExportService.class);

    private final ProductRepository productRepository;

    public CsvExportService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void writeToCsv(Writer writer) {
        List<Product> products = productRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("id", "name", "description", "price", "location", "brand", "type", "material", "upper", "outsole", "insole");
            for (Product product : products) {
                csvPrinter.printRecord(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getLocation(), product.getBrand(),
                        product.getType(), product.getMaterial(), product.getUpper(), product.getOutsole(), product.getInsole());
            }
        } catch (IOException e) {
            log.error("Error while writing CSV");
        }
    }



}
