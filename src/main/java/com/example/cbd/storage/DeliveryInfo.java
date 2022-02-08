package com.example.cbd.storage;

import javax.persistence.*;

@Entity
@Table
public class DeliveryInfo {
    @Id
    @SequenceGenerator(
            name = "deliveryinfo_sequence",
            sequenceName = "deliveryinfo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "deliveryinfo_sequence"
    )
    @Column
    private Long id;

    // in days
    @Column
    private int deliveryTime;
    @Column
    private int amount;
    @Column
    private String location;

    public DeliveryInfo() {
    }

    public DeliveryInfo(Long id, int deliveryTime, int amount, String location) {
        this.id = id;
        this.deliveryTime = deliveryTime;
        this.amount = amount;
        this.location = location;
    }

    public DeliveryInfo(int deliveryTime, int amount, String location) {
        this.deliveryTime = deliveryTime;
        this.amount = amount;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", deliveryTime=" + deliveryTime + '\'' +
                ", amount=" + amount + '\'' +
                ", location=" + location + '\'' +
                '}';
    }
}

