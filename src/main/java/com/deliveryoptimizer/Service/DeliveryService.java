package com.deliveryoptimizer.Service;

import com.deliveryoptimizer.Model.Delivery;
import com.deliveryoptimizer.Model.Vehicle;
import com.deliveryoptimizer.Repositories.DeliveryRepository;

public class DeliveryService implements DeleveryServiceInrerface {

   private final DeliveryRepository deliveryRepository;

   public DeliveryService(DeliveryRepository deliveryRepository) {
       this.deliveryRepository = deliveryRepository;
   }

   public Delivery create(Delivery delivery) {
       return deliveryRepository.save(delivery);
   }
   public Delivery update(Delivery delivery) {
       return deliveryRepository.save(delivery);
   }

   public void  delete(int id) {
       deliveryRepository.deleteById(id);
   }
}
