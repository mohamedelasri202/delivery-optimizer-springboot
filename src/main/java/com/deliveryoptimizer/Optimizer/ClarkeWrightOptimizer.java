package com.deliveryoptimizer.Optimizer;

import com.deliveryoptimizer.Model.Delivery;
import com.deliveryoptimizer.Model.Vehicle;
import com.deliveryoptimizer.Model.Warehouse;
import com.deliveryoptimizer.util.DistanceCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ClarkeWrightOptimizer implements TourOptimizer {

    @Override
    public List<Delivery> calculateOptimalTour(Vehicle vehicle, Warehouse warehouse, List<Delivery> deliveries) {
        if (deliveries == null || deliveries.isEmpty()) {
            return new ArrayList<>();
        }

        // Step 1: Initialize each delivery as its own route (W -> d -> W)
        List<List<Delivery>> routes = new ArrayList<>();
        for (Delivery d : deliveries) {
            List<Delivery> route = new ArrayList<>();
            route.add(d);
            routes.add(route);
        }

        // Step 2: Compute savings for each pair of deliveries
        class Saving implements Comparable<Saving> {
            Delivery d1, d2;
            double saving;

            Saving(Delivery d1, Delivery d2, double saving) {
                this.d1 = d1;
                this.d2 = d2;
                this.saving = saving;
            }

            @Override
            public int compareTo(Saving o) {
                return Double.compare(o.saving, this.saving); // descending sort
            }
        }

        List<Saving> savingsList = new ArrayList<>();
        for (int i = 0; i < deliveries.size(); i++) {
            for (int j = i + 1; j < deliveries.size(); j++) {
                Delivery d1 = deliveries.get(i);
                Delivery d2 = deliveries.get(j);

                // FIX 1: Correct DistanceCalc parameter order (LON, LAT, LON, LAT)
                double dist_W_d1 = DistanceCalculator.DistanceCalc(
                        warehouse.getLongitude(), warehouse.getLatitude(),
                        d1.getLongitude(), d1.getLatitude()
                );
                double dist_W_d2 = DistanceCalculator.DistanceCalc(
                        warehouse.getLongitude(), warehouse.getLatitude(),
                        d2.getLongitude(), d2.getLatitude()
                );
                double dist_d1_d2 = DistanceCalculator.DistanceCalc(
                        d1.getLongitude(), d1.getLatitude(),
                        d2.getLongitude(), d2.getLatitude()
                );

                double s = dist_W_d1 + dist_W_d2 - dist_d1_d2;
                savingsList.add(new Saving(d1, d2, s));
            }
        }

        // Step 3: Sort savings descending
        Collections.sort(savingsList);

        // Step 4: Merge routes based on savings, focusing ONLY on distance logic
        for (Saving s : savingsList) {
            List<Delivery> route1 = findRoute(routes, s.d1);
            List<Delivery> route2 = findRoute(routes, s.d2);

            if (route1 == route2) continue; // Already in the same route

            // Perform Endpoint Check and Merge
            List<Delivery> mergedRoute = mergeRoutesIfValid(route1, route2, s.d1, s.d2);

            if (mergedRoute != null) {
                routes.remove(route1);
                routes.remove(route2);
                routes.add(mergedRoute);
            }
        }

        // Step 5: Flatten all routes into one ordered list
        // Since this VRP project implies a single vehicle/tour, all resulting routes are merged
        // into the final ordered list.
        List<Delivery> optimizedDeliveries = new ArrayList<>();
        for (List<Delivery> route : routes) {
            optimizedDeliveries.addAll(route);
        }

        return optimizedDeliveries;
    }

    // Helper method to find the route containing a specific delivery
    private List<Delivery> findRoute(List<List<Delivery>> routes, Delivery d) {
        for (List<Delivery> route : routes) {

            if (route.stream().anyMatch(delivery -> delivery.getId() == d.getId())) {
                return route;
            }
        }
        return null;
    }

//    /**
//     * Checks if d1 and d2 are endpoints of their respective routes (adjacent to the Warehouse)
//     * and performs the VRP merge.
//     * @return The new, merged route list, or null if the merge is invalid (internal nodes).
//     */
    private List<Delivery> mergeRoutesIfValid(List<Delivery> r1, List<Delivery> r2, Delivery d1, Delivery d2) {
        // Find d1's position in r1 (Head=0, Tail=size-1)
        int d1Index = r1.indexOf(d1);
        boolean d1IsHead = d1Index == 0;
        boolean d1IsTail = d1Index == r1.size() - 1;

        // Find d2's position in r2
        int d2Index = r2.indexOf(d2);
        boolean d2IsHead = d2Index == 0;
        boolean d2IsTail = d2Index == r2.size() - 1;

        List<Delivery> merged = null;

        // Rule: At least one point in each route must be an endpoint being merged.

        // 1. Tail-to-Head (d1 is tail of r1, d2 is head of r2) -> R1 + R2
        if (d1IsTail && d2IsHead) {
            merged = new ArrayList<>(r1);
            merged.addAll(r2);
        }
        // 2. Head-to-Tail (d2 is tail of r2, d1 is head of r1) -> R2 + R1
        else if (d2IsTail && d1IsHead) {
            merged = new ArrayList<>(r2);
            merged.addAll(r1);
        }
        // 3. Head-to-Head (d1 is head of r1, d2 is head of r2) -> R1_reversed + R2
        else if (d1IsHead && d2IsHead) {
            List<Delivery> r1Rev = new ArrayList<>(r1);
            Collections.reverse(r1Rev);
            merged = new ArrayList<>(r1Rev);
            merged.addAll(r2);
        }
        // 4. Tail-to-Tail (d1 is tail of r1, d2 is tail of r2) -> R1 + R2_reversed
        else if (d1IsTail && d2IsTail) {
            List<Delivery> r2Rev = new ArrayList<>(r2);
            Collections.reverse(r2Rev);
            merged = new ArrayList<>(r1);
            merged.addAll(r2Rev);
        }
        // 5. If any node is an internal node, the merge is invalid.
        else {
            return null;
        }

        return merged;
    }
}