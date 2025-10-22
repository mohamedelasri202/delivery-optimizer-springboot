package com.deliveryoptimizer.Model;


import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  double weight;
    private double volume;
    private double maxNumberDelivery;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;



   public Vehicle(){

   }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getMaxNumberDelivery() {
        return maxNumberDelivery;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setMaxNumberDelivery(double maxNumberDelivery) {
        this.maxNumberDelivery = maxNumberDelivery;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
