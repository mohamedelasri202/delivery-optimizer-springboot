package com.deliveryoptimizer.Service;

import com.deliveryoptimizer.Controller.WareHouseController;
import com.deliveryoptimizer.Model.Warehouse;
import com.deliveryoptimizer.Repositories.WareHouseRepository;
import jakarta.transaction.Transactional;

public class WareHouseService implements WareHouseServiceInterface {

    private final WareHouseRepository wareHouseRepository;

    public WareHouseService(WareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }

    @Transactional
    public Warehouse createWarehouse(Warehouse warehouse) {
        return wareHouseRepository.save(warehouse);
    }


    @Transactional
    public Warehouse updateWarehouse(int id, Warehouse warehouse) {
        return wareHouseRepository.save(warehouse);
    }




    @Transactional
    public Boolean deleteWarehouse(int id) {
       return wareHouseRepository.findById(id).map(warehouse ->{
            wareHouseRepository.delete(warehouse);
            return true;
        }).orElse(false);

    }
}
