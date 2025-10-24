package com.deliveryoptimizer.Repositories;

import com.deliveryoptimizer.Model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Integer> {
}
