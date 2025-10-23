package com.deliveryoptimizer.Repositories;

import com.deliveryoptimizer.Controller.WareHouseController;
import com.deliveryoptimizer.Model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WareHouseRepository extends JpaRepository<Warehouse, Integer> {

}
