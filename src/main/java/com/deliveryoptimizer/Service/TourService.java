package com.deliveryoptimizer.Service;

import com.deliveryoptimizer.DTO.TourDTO;
import com.deliveryoptimizer.Model.Tour;
import com.deliveryoptimizer.Model.Vehicle;
import com.deliveryoptimizer.Model.Warehouse;
import com.deliveryoptimizer.Repositories.TourRepository;
import com.deliveryoptimizer.Repositories.VehicleRepository;
import com.deliveryoptimizer.Repositories.WareHouseRepository;
import com.deliveryoptimizer.Mapper.TourMapper;
import jakarta.transaction.Transactional;

public class TourService implements TourServiceInterface {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final WareHouseRepository wareHouseRepository;
    private final VehicleRepository vehicleRepository; // ✅ Add this

    public TourService(TourRepository tourRepository,
                       TourMapper tourMapper,
                       WareHouseRepository wareHouseRepository,
                       VehicleRepository vehicleRepository) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
        this.wareHouseRepository = wareHouseRepository;
        this.vehicleRepository = vehicleRepository; // ✅ initialize it
    }

    @Transactional
    public TourDTO create(TourDTO dto) {
        Tour tour = tourMapper.toEntity(dto);

        if (dto.getWarehouseId() != null) {
            Warehouse warehouse = wareHouseRepository.findById(dto.getWarehouseId())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found with id " + dto.getWarehouseId()));
            tour.setWarehouse(warehouse);
        }

        if (dto.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId().intValue())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found with id " + dto.getVehicleId()));
            tour.setVehicle(vehicle);
        }

        Tour saved = tourRepository.save(tour);
        return tourMapper.toDTO(saved);
    }

    @Transactional
    public TourDTO update(TourDTO dto) {
        Tour tour = tourMapper.toEntity(dto);
        return tourMapper.toDTO(tourRepository.save(tour));
    }

    @Transactional
    public Boolean delete(Integer id) {
        return tourRepository.findById(id).map(tour -> {
            tourRepository.delete(tour);
            return true;
        }).orElse(false);
    }
}
