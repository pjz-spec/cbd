package com.example.cbd.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class DeliveryInfoService {

    private final DeliveryInfoRepository deliveryInfoRepository;

    @Autowired
    public DeliveryInfoService(DeliveryInfoRepository deliveryInfoRepository) {
        this.deliveryInfoRepository = deliveryInfoRepository;
    }

    public List<DeliveryInfo> getAllDeliveryInfos() {
        return deliveryInfoRepository.findAll();
    }

    public DeliveryInfo getDeliveryInfo(Long id) {
        boolean exists = deliveryInfoRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException();
        }
        return deliveryInfoRepository.findById(id).get();
    }

    public void addNewDeliveryInfo(DeliveryInfo deliveryInfo) {
        deliveryInfoRepository.save(deliveryInfo);
    }

    public void deleteDeliveryInfo(Long id) {
        boolean exists = deliveryInfoRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException();
        }
        deliveryInfoRepository.findById(id);
    }

    @Transactional
    public void updateDeliveryInfo(Long id, String location) {
        DeliveryInfo deliveryInfo = deliveryInfoRepository.findById(id).orElseThrow(() -> new IllegalStateException("deliveryInfo with id " + id + " does not exist"));

        if (location != null && location.length() > 0 && !Objects.equals(deliveryInfo.getLocation(), location)) {
            deliveryInfo.setLocation(location);
        }
    }

}
