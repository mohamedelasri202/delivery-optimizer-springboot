package com.deliveryoptimizer.Service;

import com.deliveryoptimizer.Mapper.WarehouseMapper;
import com.deliveryoptimizer.Model.Warehouse;
import com.deliveryoptimizer.Repositories.WareHouseRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class WareHouseService implements WareHouseServiceInterface {

    private final WareHouseRepository wareHouseRepository;
    private final WarehouseMapper warehouseMapper;

    // Constructor injection (declared in applicationContext.xml)
    public WareHouseService(WareHouseRepository wareHouseRepository, WarehouseMapper warehouseMapper) {
        this.wareHouseRepository = wareHouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    @Transactional
    public Warehouse createWarehouse(Warehouse warehouse) {
        return wareHouseRepository.save(warehouse);
    }

    @Override
    @Transactional
    public Warehouse updateWarehouse(int id, Warehouse warehouse) {
        Optional<Warehouse> existing = wareHouseRepository.findById(id);
        if (existing.isPresent()) {
            Warehouse updatedWarehouse = existing.get();
            updatedWarehouse.setName(warehouse.getName());
            updatedWarehouse.setLatitude(warehouse.getLatitude());
            updatedWarehouse.setLongitude(warehouse.getLongitude());
            return wareHouseRepository.save(updatedWarehouse);
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean deleteWarehouse(int id) {
        return wareHouseRepository.findById(id)
                .map(warehouse -> {
                    wareHouseRepository.delete(warehouse);
                    return true;
                })
                .orElse(false);
    }
//
//    @Override
//    public List<Warehouse> getAllWarehouses() {
//        return wareHouseRepository.findAll();
//    }
//
//    @Override
//    public Optional<Warehouse> getWarehouseById(int id) {
//        return wareHouseRepository.findById(id);
//    }
}
