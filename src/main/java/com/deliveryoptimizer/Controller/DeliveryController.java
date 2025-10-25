package com.deliveryoptimizer.Controller;

import com.deliveryoptimizer.DTO.DeliveryDTO;
import com.deliveryoptimizer.Model.Delivery;
import com.deliveryoptimizer.Service.DeleveryServiceInrerface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeleveryServiceInrerface deliveryService;

    public DeliveryController(DeleveryServiceInrerface deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<Delivery> create(@RequestBody DeliveryDTO deliveryDTO) {
        Delivery created = deliveryService.create(deliveryDTO);
        return ResponseEntity.created(URI.create("/api/delivery/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> update(@PathVariable long id, @RequestBody Delivery delivery) {
        delivery.setId(id);
        Delivery updated = deliveryService.update(delivery);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        deliveryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
