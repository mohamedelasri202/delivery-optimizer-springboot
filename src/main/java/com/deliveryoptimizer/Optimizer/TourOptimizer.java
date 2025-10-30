package com.deliveryoptimizer.Optimizer;

import com.deliveryoptimizer.Model.Delivery;
import com.deliveryoptimizer.Model.Vehicle;
import com.deliveryoptimizer.Model.Warehouse;
import java.util.List;

public interface TourOptimizer {

    List<Delivery> calculateOptimalTour(Vehicle vehicle, Warehouse warehouse, List<Delivery> deliveries);
}
