package com.example.warehouse.service;

import com.example.warehouse.dto.warehouse.WarehouseDTO;

import java.util.List;

public interface WarehouseService {

    WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO);

    List<WarehouseDTO> getAllWarehouses();

    WarehouseDTO getWarehouseById(Long id);

    WarehouseDTO updateWarehouseById(Long id, WarehouseDTO warehouseDTO);

    boolean deleteWarehouseById(Long id);

    void deleteAllWarehouses();
}
