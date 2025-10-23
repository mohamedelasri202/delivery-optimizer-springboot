package com.deliveryoptimizer.Controller;


import com.deliveryoptimizer.Model.Delivery;
import com.deliveryoptimizer.Service.DeleveryServiceInrerface;
import com.deliveryoptimizer.Service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URL;

@RestController
@RequestMapping("/api/Delivery")
public class DeliveryController {


    private final DeleveryServiceInrerface deleveryServiceInrerface;
    private DeleveryServiceInrerface deliveryServiceInrerface;


    public DeliveryController(DeleveryServiceInrerface deliveryServiceInrerface, DeleveryServiceInrerface deleveryServiceInrerface) {
        this.deliveryServiceInrerface = deliveryServiceInrerface;
        this.deleveryServiceInrerface = deleveryServiceInrerface;
    }
//
    @PostMapping
    public ResponseEntity<Delivery> create(@RequestBody Delivery delivery) {
        Delivery created = deliveryServiceInrerface.create(delivery);
        return ResponseEntity.created(URI.create("/Delivery"+ created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> update(@PathVariable long id ,@RequestBody Delivery delivery) {
        delivery.setId(id);
        Delivery created = deliveryServiceInrerface.update(delivery);
        return ResponseEntity.created(URI.create("/Delivery/" + created.getId())).body(created);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void > delete(@PathVariable Integer id) {
        deleveryServiceInrerface.delete(id);
        return ResponseEntity.noContent().build();

    }

}
