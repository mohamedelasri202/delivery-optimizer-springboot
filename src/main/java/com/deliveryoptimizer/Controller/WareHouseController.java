package com.deliveryoptimizer.Controller;


import com.deliveryoptimizer.Model.Warehouse;
import com.deliveryoptimizer.Service.WareHouseServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse>updateWarehouse(@PathVariable int id, @RequestBody Warehouse warehouse) {
        warehouse.setId(id);
        return ResponseEntity.ok(warehouse);
    }






    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable Integer id) {

        Boolean delete = wareHouseServiceInterface.deleteWarehouse(id);
        if(delete){
            return ResponseEntity.ok("wareHouse with id " + id +" deleted successfully");
        }else {
            return ResponseEntity.ok("WareHouse with id " + id + " wasn't found");
        }


    }



}
