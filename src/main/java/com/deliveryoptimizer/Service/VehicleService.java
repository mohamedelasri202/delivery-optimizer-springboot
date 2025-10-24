package com.deliveryoptimizer.Service;

import com.deliveryoptimizer.DTO.VehicleDTO;
import com.deliveryoptimizer.Mapper.VehicleMapper;
import com.deliveryoptimizer.Model.Vehicle;
import com.deliveryoptimizer.Repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import java.util.List;

public class VehicleService implements VehicleServiceInterface {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Transactional
    public VehicleDTO create(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
        Vehicle saved = vehicleRepository.save(vehicle);
        return vehicleMapper.toDTO(saved);
    }

    @Transactional
    public VehicleDTO update(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
        Vehicle updated = vehicleRepository.save(vehicle);
        return vehicleMapper.toDTO(updated);
    }

    @Transactional
    public Boolean delete(int id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicleRepository.delete(vehicle);
                    return true;
                })
                .orElse(false);
    }

    public List<VehicleDTO> getAll() {
        return vehicleMapper.toDTOList(vehicleRepository.findAll());
    }
}
