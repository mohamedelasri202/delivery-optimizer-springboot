package com.deliveryoptimizer.Repositories;

import com.deliveryoptimizer.Model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}
