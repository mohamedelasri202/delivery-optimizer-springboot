package com.deliveryoptimizer.Controller;

import com.deliveryoptimizer.Model.Vehicle;
import com.deliveryoptimizer.Service.VehicleService;
import com.deliveryoptimizer.Service.VehicleServiceInterface;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleServiceInterface vehicleService;
    private final GenericResponseService responseBuilder;

    public VehicleController(VehicleServiceInterface vehicleService, GenericResponseService responseBuilder) {
        this.vehicleService = vehicleService;
        this.responseBuilder = responseBuilder;
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
//    @DeleteMapping("{/id}")
//    public ResponseEntity<Vehicle>delete(@PathVariable long id, @RequestBody Vehicle vehicle) {
//        vehicle.setId(id);
//        Vehicle delete = vehicleService.delete(id);
//        return ResponseEntity.ok(delete);
//    }

    @DeleteMapping("/{id}")
        public ResponseEntity<String> delete(@PathVariable int id) {
         Boolean delete =  vehicleService.delete(id);
            if(delete){
                return ResponseEntity.ok("Vehicle with id " + id + " deleted successfully");
            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
            }

    }

}
