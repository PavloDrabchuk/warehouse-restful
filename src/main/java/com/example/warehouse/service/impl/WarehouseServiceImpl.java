package com.example.warehouse.service.impl;

import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }


    @Override
    public void createWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> getAllWarehouse() {
        return (List<Warehouse>) warehouseRepository.findAll();
    }

    @Override
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public int updateWarehouseById(Long id, Warehouse newWarehouse) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if (warehouse.isPresent()) {
            warehouse.get().setName(newWarehouse.getName());

            warehouseRepository.save(warehouse.get());
            return 1;
        }
        return 0;
    }

    @Override
    public void deleteWarehouseById(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public void deleteAllWarehouses() {
        warehouseRepository.deleteAll();
    }
}
