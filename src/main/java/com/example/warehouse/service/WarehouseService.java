package com.example.warehouse.service;

import com.example.warehouse.model.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {

    void createWarehouse(Warehouse warehouse);

    List<Warehouse> getAllWarehouse();

    Optional<Warehouse> getWarehouseById(Long id);

    int updateWarehouseById(Long id, Warehouse newWarehouse);

    void deleteWarehouseById(Long id);

    void deleteAllWarehouses();
}
