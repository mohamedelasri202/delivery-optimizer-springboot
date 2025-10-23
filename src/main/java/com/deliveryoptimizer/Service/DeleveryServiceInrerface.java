package com.deliveryoptimizer.Service;

import com.deliveryoptimizer.Model.Delivery;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeleveryServiceInrerface {
    Delivery create(Delivery delivery);
    Delivery update(Delivery delivery);
    void delete(int id);

}
