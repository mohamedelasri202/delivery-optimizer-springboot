package com.deliveryoptimizer.Service;

import com.deliveryoptimizer.DTO.TourDTO;
import java.util.List;

public interface TourServiceInterface {
    TourDTO create(TourDTO dto);
    TourDTO update(TourDTO dto);
    Boolean delete(Integer id);
//    List<TourDTO> getAll();
//    TourDTO getById(Integer id);
}
