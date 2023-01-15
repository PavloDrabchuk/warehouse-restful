package com.example.warehouse.service;

import com.example.warehouse.model.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {

    Warehouse createWarehouse(Warehouse warehouse);

    List<Warehouse> getAllWarehouses();

    Optional<Warehouse> getWarehouseById(Long id);

    Warehouse updateWarehouseById(Long id, Warehouse newWarehouse);

    void deleteWarehouseById(Long id);

    void deleteAllWarehouses();
}
