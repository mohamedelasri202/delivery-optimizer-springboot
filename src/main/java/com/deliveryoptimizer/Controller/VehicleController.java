package com.deliveryoptimizer.Controller;

import com.deliveryoptimizer.Model.Vehicle;
import com.deliveryoptimizer.Service.VehicleService;
import com.deliveryoptimizer.Service.VehicleServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleServiceInterface vehicleService;

    public VehicleController(VehicleServiceInterface vehicleService) {
        this.vehicleService = vehicleService;
    }


    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) {
        Vehicle created = vehicleService.create(vehicle);
        return ResponseEntity.created(URI.create("/vehicles" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
        vehicle.setId(id);
        Vehicle updated = vehicleService.update(vehicle);
        return ResponseEntity.ok(updated);
    }
}
