package com.deliveryoptimizer.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Delivery {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
;
    private String address;
    private double weight;
    private double volume;


    public Delivery(){}

    public void setId(long id) {
        this.id = id;
    }



    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public long getId() {
        return id;
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public String getAddress() {
        return address;
    }



}
