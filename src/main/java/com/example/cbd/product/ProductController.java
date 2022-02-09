package com.example.cbd.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    private final ProductService productService;

    private final ProductModelAssembler assembler;

    private final CSVExportService csvExportService;

    @Autowired
    public ProductController(ProductService productService, ProductModelAssembler assembler, CSVExportService csvExportService) {
        this.productService = productService;
        this.assembler = assembler;
        this.csvExportService = csvExportService;
    }

    @GetMapping
    public CollectionModel<EntityModel<Product>> getAllProducts() {
        List<EntityModel<Product>> products = productService.getAllProducts().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }

    @GetMapping(path = "{productId}")
    public EntityModel<Product> getProduct(@PathVariable("productId") Long productId) {
        Product product = productService.getProduct(productId);
        return assembler.toModel(product);
    }

    @PostMapping
    public void registerNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
    }

    @DeleteMapping
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }

    @PutMapping(path = "{productId}")
    public void updateProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) String name) {
        productService.updateProduct(productId, name);
    }

    @PostMapping(path = "store")
    public void storeAllProducts() {
        productService.storeAllProducts();
    }

    @GetMapping(path = "export")
    public void writeToCSV(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"products.csv\"");
        csvExportService.writeToCSV(servletResponse.getWriter());
    }
}
