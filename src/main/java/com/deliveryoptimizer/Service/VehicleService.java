package com.deliveryoptimizer.Service;

import com.deliveryoptimizer.Model.Vehicle;
import com.deliveryoptimizer.Repositories.VehicleRepository;
import jakarta.transaction.Transactional;

public class VehicleService implements VehicleServiceInterface {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Vehicle update(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
