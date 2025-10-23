package com.deliveryoptimizer.Repositories;



import com.deliveryoptimizer.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}

