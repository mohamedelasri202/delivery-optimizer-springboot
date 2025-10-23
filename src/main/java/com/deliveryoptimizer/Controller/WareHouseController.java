package com.deliveryoptimizer.Controller;


import com.deliveryoptimizer.Model.Warehouse;
import com.deliveryoptimizer.Service.WareHouseServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/WarHouse")
public class WareHouseController {
    private final WareHouseServiceInterface wareHouseServiceInterface;

    public WareHouseController(WareHouseServiceInterface warehouseService) {
        this.wareHouseServiceInterface = warehouseService;

    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        wareHouseServiceInterface.createWarehouse(warehouse);
        return ResponseEntity.ok(warehouse);
    }



}
