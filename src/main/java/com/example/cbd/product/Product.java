package com.example.cbd.product;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "location", nullable = false, columnDefinition = "TEXT")
    private String location;
    @Column(name = "brand", nullable = false, columnDefinition = "TEXT")
    private String brand;
    @Column(name = "type", nullable = false, columnDefinition = "TEXT")
    private String type;
    @Column(name = "material", nullable = false, columnDefinition = "TEXT")
    private String material;
    @Column(name = "upper", nullable = false, columnDefinition = "TEXT")
    private String upper;
    @Column(name = "outsole", nullable = false, columnDefinition = "TEXT")
    private String outsole;
    @Column(name = "insole", nullable = false, columnDefinition = "TEXT")
    private String insole;

    public Product() {
    }

    public Product(Long id, String name, String description, double price, String location, String brand, String type, String material, String upper, String outsole, String insole) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.location = location;
        this.brand = brand;
        this.type = type;
        this.material = material;
        this.upper = upper;
        this.outsole = outsole;
        this.insole = insole;
    }

    public Product(String name, String description, double price, String location, String brand, String type, String material, String upper, String outsole, String insole) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.location = location;
        this.brand = brand;
        this.type = type;
        this.material = material;
        this.upper = upper;
        this.outsole = outsole;
        this.insole = insole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getUpper() {
        return upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }

    public String getOutsole() {
        return outsole;
    }

    public void setOutsole(String outsole) {
        this.outsole = outsole;
    }

    public String getInsole() {
        return insole;
    }

    public void setInsole(String insole) {
        this.insole = insole;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", material='" + material + '\'' +
                ", upper='" + upper + '\'' +
                ", outsole='" + outsole + '\'' +
                ", insole='" + insole + '\'' +
                '}';
    }

}
