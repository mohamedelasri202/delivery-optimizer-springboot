package com.deliveryoptimizer.Service;

import com.deliveryoptimizer.DTO.DeliveryDTO;
import com.deliveryoptimizer.Mapper.DeliveryMapper;
import com.deliveryoptimizer.Model.Delivery;
import com.deliveryoptimizer.Model.Tour;
import com.deliveryoptimizer.Repositories.DeliveryRepository;
import com.deliveryoptimizer.Repositories.TourRepository;
import jakarta.transaction.Transactional;

public class DeliveryService implements DeleveryServiceInrerface {

    private final DeliveryRepository deliveryRepository;
    private final TourRepository tourRepository;
    private final DeliveryMapper deliveryMapper;

    public DeliveryService(DeliveryRepository deliveryRepository, TourRepository tourRepository, DeliveryMapper deliveryMapper) {
        this.deliveryRepository = deliveryRepository;
        this.tourRepository = tourRepository;
        this.deliveryMapper = deliveryMapper;
    }

    @Transactional
    @Override
    public Delivery create(DeliveryDTO dto) {
        Delivery delivery = deliveryMapper.toEntity(dto);

        if (dto.getTourId() != null) {
            Tour tour = tourRepository.findById(dto.getTourId().intValue())
                    .orElseThrow(() -> new RuntimeException("Tour not found with id " + dto.getTourId()));
            delivery.setTour(tour);
        }

        return deliveryRepository.save(delivery);
    }

    @Transactional
    @Override
    public Delivery update(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Transactional
    @Override
    public void delete(int id) {
        deliveryRepository.deleteById(id);
    }
}
