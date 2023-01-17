package com.example.warehouse.service.impl;

import com.example.warehouse.dto.warehouse.WarehouseDTO;
import com.example.warehouse.mapper.WarehouseMapper;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) {
        return warehouseMapper.toWarehouseDTO(warehouseRepository.save(warehouseMapper.toWarehouse(warehouseDTO)));
    }

    @Override
    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(warehouseMapper::toWarehouseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseDTO getWarehouseById(Long id) {
        return warehouseMapper.toWarehouseDTO(warehouseRepository.findById(id).orElse(null));
    }

    @Override
    public WarehouseDTO updateWarehouseById(Long id, WarehouseDTO warehouseDTO) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if (warehouse.isPresent()) {
            warehouseMapper.updateWarehouseFromDTO(warehouseDTO, warehouse.get());

            return warehouseMapper.toWarehouseDTO(warehouseRepository.save(warehouse.get()));
        }
        return null;
    }

    @Override
    public boolean deleteWarehouseById(Long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if (warehouse.isPresent()) {
            warehouseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
